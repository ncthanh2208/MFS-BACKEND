package com.dxc.fresher.authservice.controllers;


import com.dxc.fresher.authservice.api.UserApi;
import com.dxc.fresher.authservice.exceptions.ApiException;
import com.dxc.fresher.authservice.models.AuthenticationRequest;
import com.dxc.fresher.authservice.models.AuthenticationResponse;
import com.dxc.fresher.authservice.models.UserModel;
import com.dxc.fresher.authservice.services.MyUserDetailsService;
import com.dxc.fresher.authservice.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserApi userApi;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (ApiException e) {
            throw new ApiException("Incorrect username or password", HttpStatus.FORBIDDEN);
        }

        UserModel userResults = userApi.findByUserName(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userResults.getUserName());

        return ResponseEntity.ok(new AuthenticationResponse(token, userResults.getRole(), userResults.getLevel()));
    }

}
