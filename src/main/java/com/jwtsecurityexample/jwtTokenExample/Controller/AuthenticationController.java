package com.jwtsecurityexample.jwtTokenExample.Controller;

import com.jwtsecurityexample.jwtTokenExample.Models.ApplicationUser;
import com.jwtsecurityexample.jwtTokenExample.Models.LoginResponseDTO;
import com.jwtsecurityexample.jwtTokenExample.Models.RegistractionDTO;
import com.jwtsecurityexample.jwtTokenExample.UserService.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")


public class AuthenticationController {
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    private AuthenticationService authenticationService;


    @PostMapping("/register")
    public ApplicationUser registerUser(@RequestBody RegistractionDTO reg){
        System.out.println("its work");

        System.out.println(reg);
        return authenticationService.registerUser(reg.getUsername(),reg.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistractionDTO body){
        System.out.println("login work");
        System.out.println(body);
        return authenticationService.loginUser(body.getUsername(), body.getPassword());
    }


}
