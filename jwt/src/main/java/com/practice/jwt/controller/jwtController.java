package com.practice.jwt.controller;

import com.practice.jwt.helper.jwtutil;
import com.practice.jwt.model.JwtResponse;
import com.practice.jwt.model.jwtRequest;
import com.practice.jwt.service.customUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class jwtController {
    @Autowired
    private customUserDetailService detailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private jwtutil jwtutil;

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody jwtRequest request) throws Exception {
        System.out.println(request);
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("Bad Credential");
        }catch (BadCredentialsException e){
            e.printStackTrace();
//            throw new Exception("Bad Credential");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Username Or password...");
        }

        
        
        //If authentication is successfulll then

        UserDetails userDetails = this.detailService.loadUserByUsername(request.getUsername());

        String token = this.jwtutil.generateToken(userDetails);
        System.out.println("Token Is: "+token);

        return ResponseEntity.ok(new JwtResponse(token));
    }
}
