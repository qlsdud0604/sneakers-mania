package com.example.sneakersmania.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity   // 스프링 시큐리티 필터가 스프링 필터 체인에 등록됨
@EnableGlobalMethodSecurity(prePostEnabled = true)   // "@PreAuthorize" 애너테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/auth/**", "/js/**", "/css/**", "/image/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/auth/loginForm");
    }
}
