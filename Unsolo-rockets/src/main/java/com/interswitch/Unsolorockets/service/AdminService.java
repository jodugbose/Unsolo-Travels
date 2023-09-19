package com.interswitch.Unsolorockets.service;

import com.interswitch.Unsolorockets.dtos.requests.PackageDto;
import com.interswitch.Unsolorockets.exceptions.PackageException;

public interface AdminService {
    String createPackage(PackageDto packageDto) throws PackageException;
}
