package com.interswitch.Unsolorockets.controllers;

import com.interswitch.Unsolorockets.service.KYCService;
import com.interswitch.Unsolorockets.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/vi/unsolo/")
@RequiredArgsConstructor
public class KYCController {
    private final UserService userService;
    private final KYCService kycService;
    @GetMapping("/nin/{nin_id}")
    public String verifyBVN(@PathVariable(value = "nin_id")  String ninId) throws Exception {
        return kycService.apiCallToBeeceptor(ninId);

    }
}
