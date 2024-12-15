package br.com.plann.er.Plann.er.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "TB_VIAGEM")
public class ViagemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID viagemId;

    @NotBlank(message = "The field [city] cannot be empty")
    private String city;

    @NotBlank(message = "The field [country] cannot be empty")
    private String country;

    @NotNull
    private LocalDate departureDate;

    @NotNull
    private LocalDate returnDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @OneToMany
    List<ConvidadoEntity> convidados;

    @OneToMany
    List<AtividadeEntity> atividades;

    @OneToMany
    List<LinkEntity> links;
}
