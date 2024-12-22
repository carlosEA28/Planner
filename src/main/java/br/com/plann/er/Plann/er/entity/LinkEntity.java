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
@Table(name = "TB_LINK")
public class LinkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID linkId;

    @NotBlank(message = "The field [name] cannot be empty")
    private String name;

    @NotBlank(message = "The field [link] cannot be empty")
    private String link;

    @ManyToOne
    @JoinColumn(name = "viagem_id")
    private ViagemEntity viagem;

    public UUID getLinkId() {
        return linkId;
    }

    public void setLinkId(UUID linkId) {
        this.linkId = linkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ViagemEntity getViagem() {
        return viagem;
    }

    public void setViagem(ViagemEntity viagem) {
        this.viagem = viagem;
    }
}
