package br.com.plann.er.Plann.er.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record UserResetPasswordDto(@NotBlank String token, @NotBlank @Min(4) String password) {
}
