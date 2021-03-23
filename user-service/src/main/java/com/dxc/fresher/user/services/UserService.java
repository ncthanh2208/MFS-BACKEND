package com.dxc.fresher.user.services;

import com.dxc.fresher.user.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService{

    public User loadByUserName(String userName);

    public List<User> getAllUser();

    public User findByUserName(String name);

    public void save(User user);

    public void update(User user,int id);

    public void deleteById(int id);

}
