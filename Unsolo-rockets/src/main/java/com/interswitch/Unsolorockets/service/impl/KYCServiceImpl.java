//package com.interswitch.Unsolorockets.service.impl;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.interswitch.Unsolorockets.dtos.responses.KYCResponse;
//import com.interswitch.Unsolorockets.respository.KYCRepository;
//import com.interswitch.Unsolorockets.respository.TravellerRepository;
//import com.interswitch.Unsolorockets.service.KYCService;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@Service
//@NoArgsConstructor
//@RequiredArgsConstructor
//@AllArgsConstructor
//public class KYCServiceImpl implements KYCService {
//
//    private final WebClient webClient;
//    private final KYCRepository kycRepository;
//    private final TravellerRepository travellerRepository;
//    @Value("${validateNIN}")
//    String beeceptorUrl;
//
//
//    public KYCServiceImpl(WebClient.Builder builder, TravellerRepository travellerRepository, KYCRepository kycRepository) {
//        this.webClient = builder
//                .baseUrl(beeceptorUrl)
//                .defaultHeader("Content-Type", "application/json")
//                .defaultHeader("Accept", "application/json")
//                .build();
//        this.travellerRepository = travellerRepository;
//        this.kycRepository = kycRepository;
//    }
//
//
//    @Override
//    public String apiCallToBeeceptor(String request) {
//
//        Flux<String> response = webClient
//                .get()
//                .uri("/nin/{}")
//                //.body(Flux.just(request), NinRequestDto.class)
//                .retrieve()
//                .bodyToFlux(String.class);
//        response.doOnNext(System.out::println);
//        Mono<String> resultMono = response.reduce((accumulator, next) -> accumulator + next);
//        String result = resultMono.block();
//
//        return result;
//    }
//
//    public void convertJsonToJavaObject(String response) throws Exception {
//
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            KYCResponse apiResponse = objectMapper.readValue(response, KYCResponse.class);
//
//            // Now 'apiResponse' contains the Java object representation of the JSON response
//            System.out.println("Status: " + apiResponse.getStatus());
//            System.out.println("isVerified: " + apiResponse.isVerified());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
////    @Override
////    public String validateNIN(KYCRequestDto kycRequestDto) throws Exception {
////        String nin = kycRequestDto.getNinId();
////        Long userId = travellerRepository.findByEmail(nin).get().getId();
////
////        // validate email input
////        User id = travellerRepository.findByEmail(kycRequestDto.getEmail()).orElseThrow(
////                () -> new InvalidCredentialsException("Invalid Credentials"));
////
////        //Validate NIN length, null, input t
////        if (!AppUtils.validNINDtoRequest(kycRequestDto.getNinId())) {
////            throw new IllegalArgumentException("NIN must be 11 digits, and must not be null");
////        }
////        if (kycRepository.findByNin(nin).isPresent()) {
////            throw new UserAlreadyExistException("An account with this NIN already exists");
////        }
////        else {
////            //NinRequestDto requestDto = new NinRequestDto();
////            requestDto.setNinId(nin);
////            String response = apiCallToBeeceptor(requestDto);
////
////            convertJsonToJavaObject(response);
////            KYCResponse kycResponse = new KYCResponse();
//
////            if (!kycResponse.getStatus().equalsIgnoreCase("success") && response.equals(true)) {
////                Kyc kyc = Kyc.builder()
////                        .userId(userId)
////                        .nin(nin)
////                        .status(kycResponse.getStatus())
////                        .createdAt(LocalDateTime.now())
////                        .build();
////                kycRepository.save(kyc);
////            }
////        }
////        return "NIN successfully saved";
////
////    }
//}

package com.interswitch.Unsolorockets.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interswitch.Unsolorockets.dtos.responses.KYCResponse;
import com.interswitch.Unsolorockets.respository.KYCRepository;
import com.interswitch.Unsolorockets.respository.TravellerRepository;
import com.interswitch.Unsolorockets.service.KYCService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class KYCServiceImpl implements KYCService {

    private final WebClient webClient;
    private final KYCRepository kycRepository;
    private final TravellerRepository travellerRepository;
    @Value("${validateNIN}")
    private String beeceptorUrl;

    @Autowired
    public KYCServiceImpl(WebClient.Builder builder, KYCRepository kycRepository, TravellerRepository travellerRepository) {
        this.webClient = builder
                .baseUrl(beeceptorUrl)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .build();
        this.kycRepository = kycRepository;
        this.travellerRepository = travellerRepository;
    }

    @Override
    public String apiCallToBeeceptor(String request) {
        Flux<String> response = webClient
                .get()
                .uri("/nin/{}")
                .retrieve()
                .bodyToFlux(String.class);
        response.doOnNext(System.out::println);
        Mono<String> resultMono = response.reduce((accumulator, next) -> accumulator + next);
        return resultMono.block();
    }

    public void convertJsonToJavaObject(String response) throws Exception {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            KYCResponse apiResponse = objectMapper.readValue(response, KYCResponse.class);
            // Now 'apiResponse' contains the Java object representation of the JSON response
            System.out.println("Status: " + apiResponse.getStatus());
            System.out.println("isVerified: " + apiResponse.isVerified());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

