package com.ajay.cabXpress.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

        @SuppressWarnings("removal")
        @Bean
        public SecurityFilterChain getSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

            httpSecurity.csrf().disable()
                    .authorizeHttpRequests()
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/api/register").permitAll()
                    .requestMatchers("/api/driver/**").hasAnyRole("DRIVER", "CAB")
                    .requestMatchers("/api/customer/**").hasRole("CUSTOMER")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll()
                    .and()
                    .authenticationProvider(getDaoAuthenticationProvider());;

            return httpSecurity.build();
        }


        @Bean
        public BCryptPasswordEncoder getPasswordEncoder(){
            return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService getUserDetailsService(){

            return new CustomUserDetailsService();
        }

        @Bean
        public DaoAuthenticationProvider getDaoAuthenticationProvider(){
            DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
            daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
            daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());

            return daoAuthenticationProvider;
        }




    }


