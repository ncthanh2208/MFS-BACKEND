package com.dxc.fresher.user.services;

import com.dxc.fresher.user.entities.User;
import com.dxc.fresher.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;


@Service
public class UserServiceImlp implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void save(User user) {
        User userDb = new User();
        userDb.setLevel(user.getLevel());
        userDb.setUserName(user.getUserName());
        userDb.setEmail(user.getEmail());
        userDb.setPassWord(user.getPassWord());
        userDb.setLevel("Bronze");
        userDb.setRole("ROLE_USER");
        userDb.setDate(LocalDate.now());
        userRepository.save(userDb);
    }

    @Override
    @Transactional
    public void update(User user){
            userRepository.save(user);

    }

    @Override
    @Transactional
    public User findByUserName(String name){
        User result = userRepository.findByUserName(name);
        return new User(result.getId(),result.getDailyLimit(),result.getDate(),result.getLevel(),result.getUserName(),result.getEmail(), result.getPassWord(),result.getRole());
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User loadByUserName(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new User(user.getUserName(), user.getPassWord(),user.getRole()) ;
    }

}
