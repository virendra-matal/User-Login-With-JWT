package com.practice.jwt.service;

import com.practice.jwt.Repository.UserRepo;
import com.practice.jwt.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class customUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String Username) throws UsernameNotFoundException {

        User user = userRepo.findByUsername(Username);

        if (user==null){
            throw  new UsernameNotFoundException("User Not found!!!!");

        }else {
            return  new CustomUserDetails(user);
        }


//        if(Username.equals("Virendra")){
//            return new User("Virendra","veer",new ArrayList<>());
//        }else {
//            throw new UsernameNotFoundException(Username);
//

    }
}
