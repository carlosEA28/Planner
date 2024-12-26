package br.com.plann.er.Plann.er.controller;

import br.com.plann.er.Plann.er.dto.*;
import br.com.plann.er.Plann.er.entity.UserEntity;
import br.com.plann.er.Plann.er.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/viagem/{viagemId}/convidados")
    public ResponseEntity<List<ConvidadoDto>> getConvidadosPorViagem(@PathVariable String viagemId) {
        List<ConvidadoDto> convidados = userService.getConvidados(viagemId);
        return ResponseEntity.ok(convidados);
    }

}
