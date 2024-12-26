package br.com.plann.er.Plann.er.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
//@Getter
//@Setter
@Table(name = "TB_ATIVIDADE")
public class AtividadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID atividadeId;

    @NotBlank(message = "The field [atividadeName] cannot be empty")
    private String atividadeName;

    private LocalDate data;

    private Boolean isDone;

    private String hour;

    @ManyToOne
    @JoinColumn(name = "viagem_id")
    private ViagemEntity viagem;

    public UUID getAtividadeId() {
        return atividadeId;
    }

    public void setAtividadeId(UUID atividadeId) {
        this.atividadeId = atividadeId;
    }

    public String getAtividadeName() {
        return atividadeName;
    }

    public void setAtividadeName(String atividadeName) {
        this.atividadeName = atividadeName;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public ViagemEntity getViagem() {
        return viagem;
    }

    public void setViagem(ViagemEntity viagem) {
        this.viagem = viagem;
    }

    public void setIsDone(boolean b) {
    }
}
