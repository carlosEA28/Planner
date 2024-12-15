package br.com.plann.er.Plann.er.entity;

import br.com.plann.er.Plann.er.Enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;

    @NotBlank(message = "The field [name] cannot be empty")
    private String name;

    @Email
    @NotBlank(message = "The field [email] cannot be empty")
    private String email;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public Instant getResetTokenExpiration() {
        return resetTokenExpiration;
    }

    public void setResetTokenExpiration(Instant resetTokenExpiration) {
        this.resetTokenExpiration = resetTokenExpiration;
    }

    public List<ViagemEntity> getMyTrips() {
        return myTrips;
    }

    public void setMyTrips(List<ViagemEntity> myTrips) {
        this.myTrips = myTrips;
    }

    @NotBlank(message = "The field [password] cannot be empty")
    private String password;

    private String resetToken;
    private Instant resetTokenExpiration;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToMany
    List<ViagemEntity> myTrips;

    public UserEntity withResetToken(String resetToken, Instant resetTokenAdditionalTime) {
        this.resetToken = resetToken;
        this.resetTokenExpiration = resetTokenAdditionalTime;
        return this;
    }

}
