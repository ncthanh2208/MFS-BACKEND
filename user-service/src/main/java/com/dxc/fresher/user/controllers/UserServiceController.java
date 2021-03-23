package com.dxc.fresher.user.controllers;

import com.dxc.fresher.user.entities.User;
import com.dxc.fresher.user.exceptions.ApiException;
import com.dxc.fresher.user.repositories.UserRepository;
import com.dxc.fresher.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            userService.update(user,id);
            return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") int id){
            userService.deleteById(id);
           return new ResponseEntity<>( HttpStatus.OK);
    }
}
