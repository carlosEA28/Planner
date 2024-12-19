package br.com.plann.er.Plann.er.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public record ViagemDto(String city, String country, LocalDate departureDate, LocalDate returnDate,
                        Set<String> emails, String userId) {
}
