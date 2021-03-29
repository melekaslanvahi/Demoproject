package com.example.demo.service;

/*
import org.springframework.com.example.demo.security.core.userdetails.User;
import org.springframework.com.example.demo.security.core.userdetails.UserDetails;
import org.springframework.com.example.demo.security.core.userdetails.UserDetailsService;
import org.springframework.com.example.demo.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.demo.repository.MemberRepository;
import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl_eski implements UserDetailsService {
    private MemberRepository memberRepository;

    public UserDetailsServiceImpl_eski(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);
        if (member == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(member.getEmail(), member.getPassword(), emptyList());
    }
}
*/

