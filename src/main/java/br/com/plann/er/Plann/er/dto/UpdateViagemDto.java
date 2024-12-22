package br.com.plann.er.Plann.er.dto;

import java.time.LocalDate;

public record UpdateViagemDto(String city, String country, LocalDate departureDate, LocalDate returnDate) {
}
