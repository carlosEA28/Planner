package br.com.plann.er.Plann.er.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "TB_ATIVIDADE")
public class AtividadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID atividadeId;

    @NotBlank(message = "The field [atividadeName] cannot be empty")
    private String atividadeName;

    private Boolean isDone;

    private String hour;

    @ManyToOne
    @JoinColumn(name = "viagem_id")
    private ViagemEntity viagem;
}
