package com.interswitch.Unsolorockets.controllers;

import com.interswitch.Unsolorockets.dtos.requests.PackageDto;
import com.interswitch.Unsolorockets.exceptions.PackageException;
import com.interswitch.Unsolorockets.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin")
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/")
    public ResponseEntity<?> createPackage(@RequestBody PackageDto packageDto) throws PackageException {
        var response = adminService.createPackage(packageDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
