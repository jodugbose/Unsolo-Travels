package com.interswitch.Unsolorockets.service.payment;

import com.interswitch.Unsolorockets.exceptions.CommonsException;
import com.interswitch.Unsolorockets.exceptions.UserNotFoundException;
import com.interswitch.Unsolorockets.models.Traveller;
import com.interswitch.Unsolorockets.respository.Transaction;
import com.interswitch.Unsolorockets.respository.TransactionRepository;
import com.interswitch.Unsolorockets.respository.TravellerRepository;
import com.interswitch.Unsolorockets.respository.WalletRepository;
import com.interswitch.Unsolorockets.utils.IAppendableReferenceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final FlutterwaveService flutterwaveService;

    private final TravellerRepository travellerRepository;

    private final WalletRepository walletRepository;

    private final TransactionRepository transactionRepository;

    @Transactional
    public PaymentInitiationResponse initiatePayment(Long userId, PaymentRequestDto paymentRequestDto) throws CommonsException {

        Traveller traveller = travellerRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        Customer customer = new Customer();
        customer.setName(traveller.getFirstName() + " " + traveller.getLastName());
        customer.setEmail(traveller.getEmail());
        customer.setPhoneNo(traveller.getPhoneNumber());
        paymentRequestDto.setCustomer(customer);
        PaymentLogDto paymentLogDto = createPaymentLog(userId, paymentRequestDto);
        paymentRequestDto.setPaymentReference(paymentLogDto.getReference());

        PaymentInitiationResponse response = flutterwaveService.generatePaymentLink(paymentRequestDto);
        response.setCurrency(paymentRequestDto.getCurrencyCode());
        updatePaymentLog(userId, paymentLogDto.getReference(), response);
        return response;

    }

    private void updatePaymentLog(Long userId, String reference, PaymentInitiationResponse response) throws CommonsException {
        long id = IAppendableReferenceUtils.getIdFrom(reference);
        Transaction transaction = transactionRepository.findByIdAndUserId(id, userId).orElseThrow(() -> new CommonsException("transaction does not exist", HttpStatus.NOT_FOUND));
        transaction.setProcessorReference(response.getProcessorReference());
        transaction.setProcessor(response.getProcessor().name());
        transaction.setStatus(response.getStatus());
        transaction.setPaymentLink(response.getPaymentLink());
        transactionRepository.save(transaction);
    }

    private PaymentLogDto createPaymentLog(Long userId, PaymentRequestDto paymentRequestDto) {
        Transaction transaction = new Transaction();
        transaction.setUserId(userId);
        transaction.setTransactionReference(transaction.getReference());
        BeanUtils.copyProperties(paymentRequestDto, transaction);
        transaction.setStatus(PaymentStatus.NEW);

        transaction = transactionRepository.save(transaction);

        return PaymentLogDto.fromPaymentLog(transaction);
    }
}
