package com.AbhishekSharma.product.service;

import com.AbhishekSharma.product.entity.User;
import com.AbhishekSharma.product.repository.UserRepository;
import com.AbhishekSharma.product.security.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public User createUser(User user){
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user.get());
    }
}
