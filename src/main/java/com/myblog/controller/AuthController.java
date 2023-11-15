package com.myblog.controller;


import com.myblog.entity.Role;
import com.myblog.entity.User;
import com.myblog.payload.JwtAuthResponse;
import com.myblog.payload.LoginDto;
import com.myblog.payload.SignUpDto;
import com.myblog.repository.RoleRepository;
import com.myblog.repository.UserRepository;
import com.myblog.security.JwtTokenProvider;
import org.aspectj.weaver.SignatureUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @Autowired
    private AuthenticationManager authenticationManager; // responsible for  authentication user users and ensuring that
                                                        // onl  y authozired user can access protected resources

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody SignUpDto signUpDto) {
        if (userRepo.existsByUsername(signUpDto.getUsername())) {
            return new ResponseEntity<>("User already exits", HttpStatus.BAD_REQUEST);
        }
        if (userRepo.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("User email already exits", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepo.findByName("ROLE_ADMIN").get();

        Set<Role> role = new HashSet<>();
        role.add(roles);
        user.setRoles(role);

        User savedUser = userRepo.save(user);

        SignUpDto dto = new SignUpDto();
        dto.setName(savedUser.getName());
        dto.setUsername(savedUser.getUsername());
        dto.setEmail(savedUser.getEmail());
        // dto.setPassword(savedUser.getPassword());


        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }


//    @PostMapping("/signin")
//    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword())
//        );
//        SecurityContextHolder.getContext().setAuthentication(authentication); // token generated is kept in SecurityContextHolder
//        return new ResponseEntity<>("User signed in successfully!.", HttpStatus.OK);
//    }

    // --------------------------signIn Endpoint---------------------------------------------
    @PostMapping("/signIn")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        // return new ResponseEntity<>("User Sign-In successfully", HttpStatus.OK);

        // get token from tokenProvider
        String token = jwtTokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthResponse(token));

    }
}

