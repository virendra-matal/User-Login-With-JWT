package com.practice.jwt.service;

import com.practice.jwt.helper.jwtutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticateFilter extends OncePerRequestFilter {

    @Autowired
    private customUserDetailService userDetailService;

    @Autowired
    private jwtutil jwtutil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {


        String requestHeader = httpServletRequest.getHeader("Authorization");
        String username=null;
        String jwtToken=null;

        if(requestHeader!=null && requestHeader.startsWith("Bearer ")){
            jwtToken=requestHeader.substring(7);


            try {

                username = this.jwtutil.extractUsername(jwtToken);

            }catch (Exception e){
                e.printStackTrace();
            }

            UserDetails userDetails = userDetailService.loadUserByUsername(username);
            if(username != null && SecurityContextHolder.getContext().getAuthentication()==null){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else {
                System.out.println("Token is not Valid.....");
            }


        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }
}
