package br.com.plann.er.Plann.er.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDto(@Email @NotBlank String email, String password, String role) {
}
