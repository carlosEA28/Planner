package br.com.plann.er.Plann.er.dto;

import jakarta.validation.constraints.Email;

public record UserReedemPasswordDto(@Email String email) {
}
