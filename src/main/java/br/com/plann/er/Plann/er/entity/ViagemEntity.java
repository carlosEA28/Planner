package br.com.plann.er.Plann.er.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
//@Getter
//@Setter
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

    @ElementCollection // é usado para mapear coleções simples como String, números ou objetos,coisas que não são entidadess
    @CollectionTable(name = "viagem_convidados", joinColumns = @JoinColumn(name = "viagem_id"))
    Set<String> convidados;

    @OneToMany
    List<AtividadeEntity> atividades;

    @OneToMany
    List<LinkEntity> links;

    public UUID getViagemId() {
        return viagemId;
    }

    public void setViagemId(UUID viagemId) {
        this.viagemId = viagemId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Set<String> getConvidados() {
        return convidados;
    }

    public void setConvidados(Set<String> convidados) {
        this.convidados = convidados;
    }

    public List<AtividadeEntity> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<AtividadeEntity> atividades) {
        this.atividades = atividades;
    }

    public List<LinkEntity> getLinks() {
        return links;
    }

    public void setLinks(List<LinkEntity> links) {
        this.links = links;
    }
}
