package br.com.plann.er.Plann.er.controller;

import br.com.plann.er.Plann.er.dto.UpdateViagemDto;
import br.com.plann.er.Plann.er.dto.ViagemDto;
import br.com.plann.er.Plann.er.entity.ViagemEntity;
import br.com.plann.er.Plann.er.service.ViagemService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/{viagemId}")
    public ResponseEntity<Void> updateViagem(
            @PathVariable("viagemId") String viagemId,
            @RequestBody UpdateViagemDto updateViagemDto) {
        
        viagemService.updateTrip(viagemId, updateViagemDto);

        return ResponseEntity.noContent().build();
    }

}
