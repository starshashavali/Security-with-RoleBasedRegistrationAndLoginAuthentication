package com.org.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import com.org.service.AdminServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AdminServiceImpl service;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("admin/home").permitAll() 
                .antMatchers("admin/**").hasRole("ADMIN")
                .antMatchers("user/**").hasAnyRole("USER","ADMIN")
              /*  .antMatchers("admin/userSave").hasRole("ADMIN") 
                .antMatchers("admin/getAllUserActive").hasRole("ADMIN") 
               .antMatchers("admin/EnableOrDisable/**").hasRole("ADMIN") 
                .antMatchers("admin/getAllIn-ActiveUser").hasRole("ADMIN") */
                

                		   
             /*   .antMatchers("user/getPorifile/**").hasRole("USER") 
                .antMatchers("user/login/**").hasRole("USER") 
                .antMatchers("user/getPorifile/edit/**").hasRole("USER") */

                .anyRequest().authenticated() 
            .and()
            .formLogin();

        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(service)
            .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }	}


