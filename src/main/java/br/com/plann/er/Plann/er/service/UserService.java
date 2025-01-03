package br.com.plann.er.Plann.er.service;

import br.com.plann.er.Plann.er.Enums.Role;
import br.com.plann.er.Plann.er.domains.EmailService;
import br.com.plann.er.Plann.er.dto.*;
import br.com.plann.er.Plann.er.entity.ConvidadoEntity;
import br.com.plann.er.Plann.er.entity.UserEntity;
import br.com.plann.er.Plann.er.entity.ViagemEntity;
import br.com.plann.er.Plann.er.exceptions.UserAlreadyExists;
import br.com.plann.er.Plann.er.exceptions.UserNotFound;
import br.com.plann.er.Plann.er.repository.ConvidadoRepository;
import br.com.plann.er.Plann.er.repository.UserRepository;
import br.com.plann.er.Plann.er.repository.ViagemRepository;
import br.com.plann.er.Plann.er.utils.JwtActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    @Value("${token.expiration.seconds:300}")
    private long tokenExpirationSeconds;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtActions jwtActions;

    @Autowired
    private ConvidadoRepository convidadoRepository;

    @Autowired
    private ViagemRepository viagemRepository;


    public UserEntity createUser(UserDto dto) {

        userRepository.findByEmail(dto.email()).ifPresent(user -> {
            throw new UserAlreadyExists();
        });

        var user = new UserEntity();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(bCryptPasswordEncoder.encode(dto.password()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }

    public LoginResponseDto loginUser(LoginDto dto) {
        var user = userRepository.findByEmail(dto.email()).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email inválido"));

        if (!bCryptPasswordEncoder.matches(dto.password(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha incorreta");
        }

        return jwtActions.jwtCreate(dto);
    }

    private void sendPasswordResetEmail(String email, String token) {

        String subject = "Redefinição de senha solicitado";

        String resetUrl = String.format("https://Planner.com/reset-password?token=%s", token); //colocar o link do frontend de resetar senha

        String body = String.format("""
                Olá,
                
                Recebemos uma solicitação para redefinir sua senha. \
                Clique no link abaixo para redefinir sua senha:
                
                %s
                
                Se você não solicitou a redefinição de senha, ignore este e-mail.
                
                Atenciosamente,
                Equipe Planner""", resetUrl);

        emailService.sendMail(email, subject, body);
    }

    public void reedemPassword(String email) {
        var user = userRepository.findByEmail(email).orElseThrow(UserNotFound::new);

        var token = UUID.randomUUID().toString();

        user.withResetToken(token, Instant.now().plusSeconds(this.tokenExpirationSeconds));

        userRepository.save(user);

        sendPasswordResetEmail(user.getEmail(), token);
    }

    public void resetPassword(UserResetPasswordDto userResetPasswordDto) {
        var customer = userRepository
                .findByResetToken(userResetPasswordDto.token())
                .orElseThrow(UserNotFound::new);

        customer.setPassword(
                bCryptPasswordEncoder.encode(userResetPasswordDto.password())
        );

        customer.withResetToken(null, null);

        userRepository.save(customer);
    }

    public List<ConvidadoDto> getConvidados(String viagemId) {
        UUID viagemUUID = UUID.fromString(viagemId);

        // Busca os convidados relacionados à viagem
        List<ConvidadoEntity> convidados = convidadoRepository.findByViagem_ViagemId(viagemUUID);

        if (convidados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum convidado encontrado para esta viagem");
        }

        return convidados.stream()
                .map(convidado -> new ConvidadoDto(
                        convidado.getName(),
                        convidado.getEmail()
                ))
                .toList();
    }


}
