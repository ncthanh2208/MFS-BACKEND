package com.dxc.fresher.apigateway.services;


import com.dxc.fresher.apigateway.api.UserApi;
import com.dxc.fresher.apigateway.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserApi userApi;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserModel userDb = userApi.loadByUserName(s);
        return new User(userDb.getUserName(), userDb.getPassWord(), userDb.getAuthorities());
    }
}