package com.ibm.flight.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.flight.dto.AuthenticationRequestDTO;
import com.ibm.flight.dto.AuthenticationResponseDTO;
import com.ibm.flight.dto.RegisterUserInfoDTO;
import com.ibm.flight.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

	@Autowired
    private final AuthenticationService authenticationService;

	/**
	 * process login authorization.
	 * 
	 * @param request loginInfo
	 * @return token info
	 */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(
            @RequestBody AuthenticationRequestDTO request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
    
	/**
	 * process user's register.
	 * 
	 * @param registerUserInfo user's register information
	 * @return token info
	 */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody RegisterUserInfoDTO registerUserInfo
    ) {
        return ResponseEntity.ok(authenticationService.register(registerUserInfo));
    }
}