package com.cweater.cweater.service;

import com.cweater.cweater.config.OwnUserDetail;
import com.cweater.cweater.entities.User;
import com.cweater.cweater.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUsername(username);
//        return (OwnUserDetail) user;
        Optional<User> user = userRepository.findByUsername(username);
        return user.map(OwnUserDetail::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }
}
