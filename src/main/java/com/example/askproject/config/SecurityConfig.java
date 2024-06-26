package com.example.askproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.askproject.config.handler.LoginAuthFailureHandler;
import com.example.askproject.config.handler.LoginAuthSuccessHandler;
import com.example.askproject.config.handler.LogoutAuthSuccesshandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder eCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private LoginAuthSuccessHandler loginAuthSuccessHandler;
    @Autowired
    private LoginAuthFailureHandler loginAuthFailureHandler;
    @Autowired
    private LogoutAuthSuccesshandler logoutAuthSuccesshandler;

    // @Bean
    // public AuthenticationSuccessHandler loginAuthSuccessHandler() {
    // return new LoginAuthSuccessHandler();
    // }

    // @Bean
    // public AuthenticationFailureHandler loginAuthFailureHandler() {
    // return new LoginAuthFailureHandler();
    // }

    // @Bean
    // public LogoutSuccessHandler logoutAuthSuccesshandler() {
    // return new LogoutAuthSuccesshandler();
    // }

    @Bean
    // 인증(로그인) & 인가(권한)에 대한 시큐리티 설정!!
    public SecurityFilterChain finteFilterChain(HttpSecurity http) throws Exception {

        // CSRF란, Cross Site Request Forgery의 약자로,
        // 한글 뜻으로는 사이트간 요청 위조를 뜻합니다.
        // https://devscb.tistory.com/123
        http.csrf(AbstractHttpConfigurer::disable);

        // 인증 & 인가 설정!!
        http
                // http request 요청에 대한 화면 접근(url path) 권한 설정
                .authorizeHttpRequests(authorize -> authorize
                        // // "/user" 와 같은 url path로 접근할 경우...
                        // .requestMatchers("/v1/**")
                        // // 인증(로그인)만 접근 가능
                        // .authenticated()
                        // .requestMatchers("/logout")
                        // // 인증(로그인)만 접근 가능
                        // .authenticated()
                        // .anyRequest().permitAll())

                        .requestMatchers("/user/**").hasAnyAuthority("USER","ADMIN")
                        .requestMatchers("/admin/**").hasAnyAuthority("ADMIN") // 인증&인가가 되면 접근 가능
                        .anyRequest().permitAll()) // 누구나 접근 가능
                

                // 인증(로그인)에 대한 설정
                .formLogin(formLogin -> formLogin
                        // Controller에서 로그인 페이지 url path
                        .loginPage("/login")
                        // 로그인 화면에서 form 테그의 action 주소(url path)
                        // 그러면, Spring Security가 로그인 검증을 진행함!!!
                        // Controller에서는 해당 "/login"을 만들 필요가 없음!!
                        .loginProcessingUrl("/login")
                        // 로그인 성공시
                        .successHandler(loginAuthSuccessHandler)
                        // .defaultSuccessUrl("/user/index")
                        // 로그인 실패시
                        .failureHandler(loginAuthFailureHandler)
                        // 그외의 모든 url path는 누구나 접근 가능
                        .permitAll())
                // 로그아웃에 대한 설정
                .logout(logout -> logout
                        // 로그아웃 요청 url path
                        .logoutUrl("/logout")
                        // 로그아웃 성공시
                        .logoutSuccessHandler(logoutAuthSuccesshandler)
                        .permitAll());

        // 위에서 설정한 인증 & 인가를 Spring Boot Configuration에 적용!!
        return http.build();
    }

}