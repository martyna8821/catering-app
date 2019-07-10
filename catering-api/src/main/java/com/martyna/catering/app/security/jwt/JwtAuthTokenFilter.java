package com.martyna.catering.app.security.jwt;

import com.martyna.catering.app.security.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


public class JwtAuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
   private JwtProvider tokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        try{
            String jwt = getJwt(httpServletRequest);
            if(jwt != null && tokenProvider.validateJwtToken(jwt)){
                String userName = tokenProvider.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }

        }catch (Exception ex){
            logger.error("Cannot authenticate");

        }
            filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getJwt(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            return authHeader.replace("Bearer ", "");
        }
        return null;
    }
}
