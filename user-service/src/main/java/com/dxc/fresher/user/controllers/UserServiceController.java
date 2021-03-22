package com.dxc.fresher.user.controllers;

import com.dxc.fresher.user.entities.User;
import com.dxc.fresher.user.exceptions.ApiRequestException;
import com.dxc.fresher.user.repositories.UserRepository;
import com.dxc.fresher.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserServiceController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @GetMapping("/{userName}")
    public ResponseEntity<User> findByUserName(@PathVariable("userName") String userName){
        return  new ResponseEntity<>(userService.findByUserName(userName),HttpStatus.OK);
    }

    @GetMapping("/admin/{userName}")
    public ResponseEntity<User> loadByUserName(@PathVariable("userName") String userName){
        return new ResponseEntity<>( userService.loadByUserName(userName),HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity addUser(@RequestBody User user){
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(@RequestBody User user,@PathVariable("id") int id) {
        Optional<User> userDb = userRepository.findById(id);
        if (userDb.isPresent()) {
            if(userDb.get().getLevel() != user.getLevel() && user.getLevel() != null){
                userDb.get().setLevel(user.getLevel());
            }else {
                userDb.get().setLevel(userDb.get().getLevel());
            }
            //------------------
            if(userDb.get().getEmail() != user.getEmail() && user.getEmail() != null){
                userDb.get().setEmail(user.getEmail());
            }else {
                userDb.get().setEmail(userDb.get().getEmail());
            }
            //------------------
            if(userDb.get().getPassWord() != user.getPassWord() && user.getPassWord() != null){
                userDb.get().setPassWord(user.getPassWord());
            }else {
                userDb.get().setPassWord(userDb.get().getPassWord());
            }
            //------------------
            if(userDb.get().getRole() != user.getRole() && user.getRole() != null){
                userDb.get().setRole(user.getRole());
            }else {
                userDb.get().setRole(userDb.get().getRole());
            }
            //------------------
            if(userDb.get().getDailyLimit() != user.getDailyLimit()){
                userDb.get().setDailyLimit(user.getDailyLimit());
            }else {
                userDb.get().setDailyLimit(userDb.get().getDailyLimit());
            }
            //------------------
            if(userDb.get().getDate() != user.getDate() && user.getDate() != null){
                userDb.get().setDate(user.getDate());
            }else {
                userDb.get().setDate(userDb.get().getDate());
            }
            userService.update(userDb.get());
            return new ResponseEntity<>( HttpStatus.OK);
        }else {
            throw new ApiRequestException("User not found!!!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") int id){

        Optional<User> result = userRepository.findById(id);
        if(result.isPresent()) {
            userService.deleteById(id);
           return new ResponseEntity<>( HttpStatus.OK);
        }else{
            throw new ApiRequestException("User not found!!!");
        }
    }
}
