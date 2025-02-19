package com.jwtsecurityexample.jwtTokenExample.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/")
    public String userHome(Authentication principal){

        return "welcome "+principal.getName();
    }
}
