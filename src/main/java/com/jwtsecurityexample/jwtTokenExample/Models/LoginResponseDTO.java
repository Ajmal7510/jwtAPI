package com.jwtsecurityexample.jwtTokenExample.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private ApplicationUser user;
    private String jwt;
}
