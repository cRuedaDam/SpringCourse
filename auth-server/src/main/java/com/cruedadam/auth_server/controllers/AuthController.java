package com.cruedadam.auth_server.controllers;

import com.cruedadam.auth_server.dtos.TokenDTO;
import com.cruedadam.auth_server.dtos.UserDTO;
import com.cruedadam.auth_server.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "login")
    public ResponseEntity<TokenDTO> jwtCreate(@RequestBody UserDTO user) {
        return ResponseEntity.ok(this.authService.login(user));
    }

    @PostMapping(path = "jwt")
    public ResponseEntity<TokenDTO> jwtValidate(@RequestHeader String accessToken) {
        return ResponseEntity.ok(this.authService.validateToken(TokenDTO.builder().accessToken(accessToken).build()));
    }
}
