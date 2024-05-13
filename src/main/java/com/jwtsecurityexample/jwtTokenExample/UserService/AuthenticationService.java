package com.jwtsecurityexample.jwtTokenExample.UserService;

import com.jwtsecurityexample.jwtTokenExample.Models.ApplicationUser;
import com.jwtsecurityexample.jwtTokenExample.Models.LoginResponseDTO;
import com.jwtsecurityexample.jwtTokenExample.Models.Role;
import com.jwtsecurityexample.jwtTokenExample.Repository.RoleRepository;
import com.jwtsecurityexample.jwtTokenExample.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    public ApplicationUser registerUser(String username ,String password){
        System.out.println("stage 1 ");

        Role userRole=roleRepository.findByAuthority("USER").get();

        ApplicationUser user=new ApplicationUser();
        Set<Role> setAuthorities=new HashSet<>();
        setAuthorities.add(userRole);
        System.out.println("stage 2 ");

        user.setAuthorities(setAuthorities);
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);

        return user;
    }


    public LoginResponseDTO loginUser(String username,String passwrod) {
        try {

            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, passwrod)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);

        } catch (AuthenticationException e) {
            return new LoginResponseDTO(null, "");

        }
    }

}
