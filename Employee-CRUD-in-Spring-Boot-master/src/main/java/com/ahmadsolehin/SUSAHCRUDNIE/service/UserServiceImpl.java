package com.ahmadsolehin.SUSAHCRUDNIE.service;

import com.ahmadsolehin.SUSAHCRUDNIE.model.User;
import com.ahmadsolehin.SUSAHCRUDNIE.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {
    @Autowired
    UserRepository userRepository;
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public boolean validate(String email,String password){
        boolean flag=false;
        User user=this.findByEmail(email);
        if(user!=null){
            if(email.equalsIgnoreCase(user.getEmail())&&password.equalsIgnoreCase(user.getPassword())){
                flag=true;

            }
        }
        return flag;
    }
}
