package com.martyna.catering.app.configuration;

import com.martyna.catering.app.security.jwt.JwtAuthEntryPoint;
import com.martyna.catering.app.security.jwt.JwtAuthTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    JwtAuthTokenFilter authenticationJwtTokenFilter(){
        return new JwtAuthTokenFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password("password")
                .roles("USER");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
       return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http)
            throws Exception {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }

}
