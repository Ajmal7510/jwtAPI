package com.jwtsecurityexample.jwtTokenExample.UserService;

import com.jwtsecurityexample.jwtTokenExample.Models.ApplicationUser;
import com.jwtsecurityexample.jwtTokenExample.Models.Role;
import com.jwtsecurityexample.jwtTokenExample.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loading loading ");
        System.out.println(username);
        Optional<ApplicationUser> user=userRepository.findByUsername(username);

        if(user.isEmpty()) throw new UsernameNotFoundException("user not present ");
        return user.get();
    }
}
