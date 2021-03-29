package com.example.demo.security;

/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.com.example.demo.security.com.example.demo.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.com.example.demo.security.com.example.demo.config.annotation.web.builders.HttpSecurity;
import org.springframework.com.example.demo.security.com.example.demo.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.com.example.demo.security.com.example.demo.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.com.example.demo.security.com.example.demo.config.http.SessionCreationPolicy;
import org.springframework.com.example.demo.security.core.userdetails.UserDetailsService;
import org.springframework.com.example.demo.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
public class WebSecurityConfig_eski extends WebSecurityConfigurerAdapter {
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurityConfig_eski(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants_eski.SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter_eski(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter_eski(authenticationManager()))
                // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
        auth.inMemoryAuthentication()
                .withUser("cem")
                .password("pass")
                .roles("ADMIN");


    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

}

 */

