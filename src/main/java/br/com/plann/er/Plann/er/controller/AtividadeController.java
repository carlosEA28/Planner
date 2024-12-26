package br.com.plann.er.Plann.er.controller;

import br.com.plann.er.Plann.er.dto.AtividadeDiaDto;
import br.com.plann.er.Plann.er.dto.AtividadeDto;
import br.com.plann.er.Plann.er.entity.AtividadeEntity;
import br.com.plann.er.Plann.er.service.AtividadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/viagens")
public class AtividadeController {

    @Autowired
    private AtividadeService atividadeService;

    @PostMapping("/{viagemId}/atividades/gerar")
    public ResponseEntity<List<AtividadeDiaDto>> gerarAtividades(@RequestBody AtividadeDto dto, AtividadeDiaDto atividadeDiaDto, @PathVariable String viagemId) {
        List<AtividadeEntity> atividades = atividadeService.gerarAtividadesPorDia(dto, atividadeDiaDto, UUID.fromString(viagemId));
        List<AtividadeDiaDto> atividadeDtos = atividades.stream()
                .map(a -> new AtividadeDiaDto(a.getData(), a.getAtividadeName(), a.getHour()))
                .toList();

        return ResponseEntity.ok(atividadeDtos);
    }

}

