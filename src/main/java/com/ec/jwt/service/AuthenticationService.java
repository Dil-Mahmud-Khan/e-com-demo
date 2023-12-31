package com.ec.jwt.service;

import com.ec.jwt.model.AuthenticationRequest;
import com.ec.jwt.model.AuthenticationResponse;
import com.ec.jwt.model.RegisterRequest;
import com.ec.model.Cart;
import com.ec.model.Role;
import com.ec.model.User;
import com.ec.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        var user= User.builder()
                .firstname(request.getFirstname())
                .username(request.getUsername())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        //repository.save(user);

        Cart cart=new Cart();
        user.setCart(cart);
        User savedUser=repository.save(user);



       var jwtToken=jwtService.generateToken(savedUser);

    return AuthenticationResponse.builder()
            .token(jwtToken)
            .user(savedUser.toUserDto())
            .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user=repository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken=jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .user(user.toUserDto())
                .build();
    }
}
