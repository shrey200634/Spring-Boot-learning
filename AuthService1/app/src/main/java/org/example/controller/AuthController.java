package org.example.controller;


import org.example.entities.RefershToken;
import org.example.response.JwtResponseDTO;
import org.example.service.JwtService;
import org.example.service.RefershTokenService;
import org.example.service.UserDetailsServiceImpl;
import org.example.model.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/v1")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RefershTokenService refershTokenService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserInfoDto userInfoDto) {
        try {
            Boolean isSignedUp = userDetailsService.signupUser(userInfoDto);

            if (Boolean.FALSE.equals(isSignedUp)) {
                return new ResponseEntity<>("User Already Exists", HttpStatus.BAD_REQUEST);
            }
            RefershToken refershToken = refershTokenService.createRefreshToken(userInfoDto.getUsername());
            String jwtToken = jwtService.generateToken(userInfoDto.getUsername());
            return ResponseEntity.ok(JwtResponseDTO.builder()
                    .accessToken(jwtToken)
                    .token(refershToken.getToken())
                    .build());
        } catch (Exception ex) {
            return new ResponseEntity<>("Exception in User Service: " + ex.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}