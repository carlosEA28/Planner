package br.com.plann.er.Plann.er.utils;

import br.com.plann.er.Plann.er.config.JwtConfig;
import br.com.plann.er.Plann.er.dto.LoginDto;
import br.com.plann.er.Plann.er.dto.LoginResponseDto;
import br.com.plann.er.Plann.er.exceptions.UserNotFound;
import br.com.plann.er.Plann.er.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtActions {

    @Autowired
    private JwtConfig jwtConfig;

    @Autowired
    private UserRepository userRepository;

    public LoginResponseDto jwtCreate(LoginDto dto) {

        var user = userRepository.findByEmail(dto.email()).orElseThrow(UserNotFound::new);

        var now = Instant.now();
        var expiresIn = 7 * 24 * 60 * 60L;
        var role = user.getRole();

        var claims = JwtClaimsSet.builder()
                .issuer("planner")
                .subject(String.valueOf(user.getUserId()))
                .issuedAt(now)
                .claim("role", role.name())
                .expiresAt(now.plusSeconds(expiresIn))
                .build();

        var jwtValue = jwtConfig.jwtEncoder().encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponseDto(jwtValue, expiresIn);
    }
}
