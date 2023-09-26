package com.interswitch.Unsolorockets.dtos.requests;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class KYCRequestDto {
    @NotBlank(message = "Field can not be empty, must contain 11 digits")
    @NotEmpty(message = "Field can not be empty, must contain 11 digits")
    @NonNull
    private String email;
    @NotBlank(message = "Field can not be empty, must contain 11 digits")
    @NotEmpty(message = "Field can not be empty, must contain 11 digits")
    @NonNull
    @Pattern(regexp = "^[0-9]{11}$", message = "NIN id must not be less than or more than 11 digits")
    private String ninId;
}
