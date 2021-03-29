package com.example.demo.service;

/*
import com.example.demo.domain.entity.User;
import org.springframework.com.example.demo.security.core.authority.SimpleGrantedAuthority;
import org.springframework.com.example.demo.security.core.userdetails.UserDetails;
import org.springframework.com.example.demo.security.core.userdetails.UserDetailsService;
import org.springframework.com.example.demo.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        List<SimpleGrantedAuthority> authorities = new java.util.ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getType()));
        return new org.springframework.com.example.demo.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
*/
