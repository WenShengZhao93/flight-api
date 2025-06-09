package com.ibm.flight.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.flight.dto.AuthenticationRequestDTO;
import com.ibm.flight.dto.AuthenticationResponseDTO;
import com.ibm.flight.dto.RegisterUserInfoDTO;
import com.ibm.flight.entity.User;
import lombok.RequiredArgsConstructor;
import com.ibm.flight.repository.UserRepository;
import com.ibm.flight.util.JwtUtil;

import jakarta.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	@Autowired
	private final UserRepository userRepository;
	
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private final JwtUtil jwtUtil;
	
	@Autowired
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

		User user = userRepository.findByUsername(request.getUsername());

		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}

		UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(user.getPasswordHash()).roles("USER").build();

		String jwtToken = jwtUtil.generateToken(userDetails);
		return AuthenticationResponseDTO.builder().token(jwtToken).build();
	}

	@Transactional
	public AuthenticationResponseDTO register(RegisterUserInfoDTO registerUserInfo) {
		User user = User.builder().username(registerUserInfo.getUsername())
				.passwordHash(passwordEncoder.encode(registerUserInfo.getPassword())).email(registerUserInfo.getEmail())
				.firstName(registerUserInfo.getFirstName()).lastName(registerUserInfo.getLastName()).build();

		userRepository.save(user);

		UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
				.password(user.getPasswordHash()).roles("USER").build();

		String jwtToken = jwtUtil.generateToken(userDetails);
		return AuthenticationResponseDTO.builder().token(jwtToken).build();
	}
}