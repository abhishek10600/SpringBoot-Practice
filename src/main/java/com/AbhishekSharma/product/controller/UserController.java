package com.AbhishekSharma.product.controller;

import com.AbhishekSharma.product.dto.UserDTO;
import com.AbhishekSharma.product.entity.User;
import com.AbhishekSharma.product.security.JwtUtil;
import com.AbhishekSharma.product.service.MyUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private MyUserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user){
        return userDetailsService.createUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO user){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        return jwtUtil.generateToken(userDetails.getUsername(), roles);
    }
}
