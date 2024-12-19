package br.com.plann.er.Plann.er.controller;

import br.com.plann.er.Plann.er.dto.ViagemDto;
import br.com.plann.er.Plann.er.entity.ViagemEntity;
import br.com.plann.er.Plann.er.exceptions.UserNotFound;
import br.com.plann.er.Plann.er.repository.UserRepository;
import br.com.plann.er.Plann.er.service.ViagemService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.UUID;

@RestController
@RequestMapping("/viagem")
public class ViagemController {

    @Autowired
    private ViagemService viagemService;

    @PostMapping("/create")
    public ResponseEntity<ViagemEntity> createViagem(@Valid @RequestBody ViagemDto dto) throws MessagingException {
        var viagem = viagemService.createTrip(dto);

        return ResponseEntity.ok().body(viagem);
    }
}
