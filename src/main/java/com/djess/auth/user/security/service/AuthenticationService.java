package com.djess.auth.user.security.service;

import com.djess.auth.user.model.Role;
import com.djess.auth.user.model.User;
import com.djess.auth.user.model.UserRepository;
import com.djess.auth.user.security.dto.AuthenticationRequest;
import com.djess.auth.user.security.dto.AuthenticationResponse;
import com.djess.auth.user.security.dto.RegisterRequest;
import com.djess.auth.user.exception.EmailAlreadyExistsException;
import com.djess.auth.user.security.user.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        log.info("New user registered: {}", user.getEmail());

        String jwtToken = jwtService.generateToken(CustomUserDetails.fromUser(user));
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(request.getEmail()));

        String jwtToken = jwtService.generateToken(CustomUserDetails.fromUser(user));

        return new AuthenticationResponse(jwtToken);
    }
}
