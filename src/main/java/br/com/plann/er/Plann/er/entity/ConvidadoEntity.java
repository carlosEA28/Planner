package br.com.plann.er.Plann.er.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "TB_CONVIDADOS")
public class ConvidadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID convidadoId;

    @NotBlank(message = "The field [name] cannot be empty")
    private String name;

    @Email
    @NotBlank(message = "The field [email] cannot be empty")
    private String email;

    private Boolean isConfirmed;

    @ManyToOne
    @JoinColumn(name = "viagem_id", nullable = false)
    private ViagemEntity viagem;

    public ViagemEntity getViagem() {
        return viagem;
    }

    public void setViagem(ViagemEntity viagem) {
        this.viagem = viagem;
    }

    public UUID getConvidadoId() {
        return convidadoId;
    }

    public void setConvidadoId(UUID convidadoId) {
        this.convidadoId = convidadoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        isConfirmed = confirmed;
    }
}
