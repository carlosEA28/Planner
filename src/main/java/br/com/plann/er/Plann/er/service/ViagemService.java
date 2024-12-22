package br.com.plann.er.Plann.er.service;

import br.com.plann.er.Plann.er.dto.UpdateViagemDto;
import br.com.plann.er.Plann.er.dto.ViagemDto;
import br.com.plann.er.Plann.er.entity.ViagemEntity;
import br.com.plann.er.Plann.er.exceptions.UserNotFound;
import br.com.plann.er.Plann.er.mail.MailService;
import br.com.plann.er.Plann.er.repository.UserRepository;
import br.com.plann.er.Plann.er.repository.ViagemRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class ViagemService {

    @Autowired
    private ViagemRepository viagemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    public ViagemEntity createTrip(ViagemDto dto) throws MessagingException {

        if (dto.emails() == null || dto.emails().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A viagem deve conter pelo menos um convidado");
        }
        var user = userRepository.findById(UUID.fromString(dto.userId()))
                .orElseThrow(UserNotFound::new);

        var viagem = new ViagemEntity();
        viagem.setUser(user);
        viagem.setCity(dto.city());
        viagem.setCountry(dto.country());
        viagem.setDepartureDate(dto.departureDate());
        viagem.setReturnDate(dto.returnDate());
        viagem.setConvidados(dto.emails());

        viagem = viagemRepository.save(viagem);

        // Envia os e-mails de confirmação para cada convidado
        for (String convidadoEmail : dto.emails()) {
            String confirmationLink = "http://localhost:8080/confirm/" + viagem.getViagemId();
            mailService.sendConfirmationMail(convidadoEmail, dto, confirmationLink);
        }

        return viagem;
    }

    public void updateTrip(String viagemId, UpdateViagemDto updateViagemDto) {
        var viagemExists = viagemRepository.findById(UUID.fromString(viagemId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Viagem não encontrada"));

        if (updateViagemDto.city() != null && !updateViagemDto.city().isBlank()) {
            viagemExists.setCity(updateViagemDto.city());
        }

        if (updateViagemDto.country() != null && !updateViagemDto.country().isBlank()) {
            viagemExists.setCountry(updateViagemDto.country());
        }

        if (updateViagemDto.departureDate() != null) {
            viagemExists.setDepartureDate(updateViagemDto.departureDate());
        }

        if (updateViagemDto.returnDate() != null) {
            viagemExists.setReturnDate(updateViagemDto.returnDate());
        }

        viagemRepository.save(viagemExists);
    }

}
