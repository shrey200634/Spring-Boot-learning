package org.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.example.request.AuthRequestDTO;
import org.example.request.RefreshTokenRequest; //
import org.example.response.JwtResponseDTO;
import org.example.entities.RefershToken;
import org.example.service.JwtService;
import org.example.service.RefershTokenService;

@RestController
@RequestMapping("/auth/v1")
public class TokenController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefershTokenService refershTokenService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDTO> AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
        // 1. Authenticate
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword())
        );

        if (authentication.isAuthenticated()) {
            // 2. Create Refresh Token
            RefershToken refershToken = refershTokenService.createRefreshToken(authRequestDTO.getUsername());

            // 3. Generate Access Token (Fixed: lowercase 'g' to match JwtService)
            return new ResponseEntity<>(JwtResponseDTO.builder()
                    .accessToken(jwtService.generateToken(authRequestDTO.getUsername()))
                    .token(refershToken.getToken())
                    .build(), HttpStatus.OK);
        } else {
            throw new RuntimeException("Invalid user request..!!");
        }
    }

    @PostMapping("/refreshToken")
    public JwtResponseDTO refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return refershTokenService.findByToken(refreshTokenRequest.getToken())
                // Fixed: Matches the typo 'varify' in your Service file
                .map(refershTokenService::varifyExpiration)
                .map(RefershToken::getUserInfo)
                .map(userInfo -> {
                    // Fixed: lowercase 'g'
                    String accessToken = jwtService.generateToken(userInfo.getUsername());
                    return JwtResponseDTO.builder()
                            .accessToken(accessToken)
                            .token(refreshTokenRequest.getToken())
                            .build();
                }).orElseThrow(() -> new RuntimeException("Refresh Token is not in DB..!!"));
    }
}