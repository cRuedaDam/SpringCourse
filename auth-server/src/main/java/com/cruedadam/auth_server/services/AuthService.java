package com.cruedadam.auth_server.services;


import com.cruedadam.auth_server.dtos.TokenDTO;
import com.cruedadam.auth_server.dtos.UserDTO;

public interface AuthService {

    TokenDTO login(UserDTO user);
    TokenDTO validateToken(TokenDTO token);
}
