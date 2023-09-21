package com.interswitch.Unsolorockets.controllers;

import com.interswitch.Unsolorockets.dtos.PageDto;
import com.interswitch.Unsolorockets.exceptions.CommonsException;
import com.interswitch.Unsolorockets.service.TransferChargeTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transfer-charge-transaction")
public class TransferChargeTransactionController {

    private final TransferChargeTransactionService transferChargeTransactionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("ADMIN")
    public PageDto getTransactionRecords(@RequestParam String pageNumber, @RequestParam String pageSize) {
        return transferChargeTransactionService.getTransactionList(Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
    }

    @PreAuthorize("ADMIN")
    @GetMapping("/{transactionId}")
    public ResponseEntity<?> getTransaction(@PathVariable String transactionId) throws CommonsException {
        return ResponseEntity.ok(transferChargeTransactionService.getTransaction(Long.valueOf(transactionId)));
    }
}
