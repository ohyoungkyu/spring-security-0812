package com.example.springsecurity1.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링 시큐리티를 스프링 필터체인에 등록(요청에 대한 인터셉터 작용)
public class SecurityConfig {

    // 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다.
    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        return http
                .csrf().disable()
                .authorizeRequests(authorize -> authorize.antMatchers("/user/**")
                        .authenticated()
                        .antMatchers("/manager/**")
                        .access("hasRole('ROLE_MANAGER') or HasRole('ROLE_ADMIN')")
                        .antMatchers("/admin/**")
                        .access("hasRole('ROLE_ADMIN')")
                        .anyRequest().permitAll()
                )
                // 인증 안된 페이지로 가면 로그인 페이지로 보냄
                .formLogin()
                .loginPage("/loginForm")
                .and()
                .build();
    }
}
