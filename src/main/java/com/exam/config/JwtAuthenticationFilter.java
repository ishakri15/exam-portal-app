package com.exam.config;

import com.exam.services.impl.UserDetailsServiceImpl;
import com.exam.utils.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String requestHeader = httpServletRequest.getHeader("Authorization");
        System.out.println(requestHeader);
        String username = null;
        String jwtToken = null;

        if(requestHeader != null && requestHeader.startsWith("Bearer ")){
            jwtToken = requestHeader.substring(7);
            try{
                username = this.jwtUtil.extractUsername(jwtToken);
            } catch (ExpiredJwtException ex){
                ex.printStackTrace();
                System.out.println("JWT Token is expired");
            }  catch (Exception ex){
                ex.printStackTrace();
                System.out.println("Unexpected exception occurred");
            }
        } else {
            System.out.println("Invalid token or Token does not starts with Bearer!");
        }
            //validate token here
            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
               final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
               if(this.jwtUtil.validateToken(jwtToken,userDetails)){

                   //token is validated
                   UsernamePasswordAuthenticationToken usernamePasswordAuthentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthentication);
               }
            } else {
                System.out.println("Token is not valid!");
            }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
