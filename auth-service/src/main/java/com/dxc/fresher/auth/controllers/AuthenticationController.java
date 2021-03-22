package com.dxc.fresher.auth.controllers;


import com.dxc.fresher.auth.api.UserApi;
import com.dxc.fresher.auth.exceptions.ApiExceptionHandler;
import com.dxc.fresher.auth.exceptions.ApiRequestException;
import com.dxc.fresher.auth.models.UserModel;
import com.dxc.fresher.auth.services.MyUserDetailsService;
import com.dxc.fresher.auth.models.AuthenticationRequest;
import com.dxc.fresher.auth.models.AuthenticationResponse;
import com.dxc.fresher.auth.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
		}
		catch (ApiRequestException e) {
			throw new ApiRequestException("Incorrect username or password", e);
		}

		UserModel userResults = userApi.findByUserName(authenticationRequest.getUsername());
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(token,userResults.getRole(),userResults.getLevel()));
	}

}
