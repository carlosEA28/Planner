package br.com.plann.er.Plann.er.dto;

import java.time.LocalDate;

public record AtividadeDiaDto(LocalDate data,
                              String descricao,
                              String hour) {
}
