package br.com.plann.er.Plann.er.controller;

import br.com.plann.er.Plann.er.dto.*;
import br.com.plann.er.Plann.er.entity.UserEntity;
import br.com.plann.er.Plann.er.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserDto dto) {
        var user = userService.createUser(dto);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginDto dto) {
        var token = userService.loginUser(dto);

        return ResponseEntity.ok().body(token);
    }

    @PostMapping("/redeem-password")
    public ResponseEntity<Map<String, String>> redeemPassword(@RequestBody @Valid UserReedemPasswordDto userReedemPasswordDto) {
        userService.reedemPassword(userReedemPasswordDto.email());

        return ResponseEntity.ok().body(Map.of("message", "Send the redeem password link to your email"));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(@RequestBody @Valid UserResetPasswordDto dto) {
        userService.resetPassword(dto);

        return ResponseEntity.ok().body(Map.of("message", "Credentials updated"));
    }
}
