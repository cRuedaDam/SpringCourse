package com.cruedadam.auth_server.services;

import com.cruedadam.auth_server.dtos.TokenDTO;
import com.cruedadam.auth_server.dtos.UserDTO;
import com.cruedadam.auth_server.entities.UserEntity;
import com.cruedadam.auth_server.helpers.JwtHelper;
import com.cruedadam.auth_server.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Transactional
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtHelper jwtHelper;
    private static final String USER_EXCEPTION_MSG = "Error to auth user";


    @Override
    public TokenDTO login(UserDTO user) {
        final var userFromDB = this.userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MSG));

        this.validPassword(user, userFromDB);

        return TokenDTO.builder().accessToken(this.jwtHelper.createToken(userFromDB.getUsername())).build();
    }

    @Override
    public TokenDTO validateToken(TokenDTO token) {

        if (this.jwtHelper.validateToken(token.getAccessToken())){
            return TokenDTO.builder().accessToken(token.getAccessToken()).build();
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MSG);
    }

    private void validPassword(UserDTO userDTO, UserEntity userEntity) {
        if (!this.passwordEncoder.matches(userDTO.getPassword(), userEntity.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, USER_EXCEPTION_MSG);
        }

    }
}
