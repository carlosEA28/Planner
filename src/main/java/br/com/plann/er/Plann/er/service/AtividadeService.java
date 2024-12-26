package br.com.plann.er.Plann.er.service;

import br.com.plann.er.Plann.er.dto.AtividadeDiaDto;
import br.com.plann.er.Plann.er.dto.AtividadeDto;
import br.com.plann.er.Plann.er.entity.AtividadeEntity;
import br.com.plann.er.Plann.er.entity.ViagemEntity;
import br.com.plann.er.Plann.er.repository.AtividadeRepository;
import br.com.plann.er.Plann.er.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private ViagemRepository viagemRepository;

    public List<AtividadeEntity> gerarAtividadesPorDia(AtividadeDto dto, AtividadeDiaDto atividadeDiaDto, UUID viagemId) {
        ViagemEntity viagem = viagemRepository.findById(viagemId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem não encontrada"));

        List<AtividadeEntity> atividades = new ArrayList<>();

        if (dto.atividades() != null && !dto.atividades().isEmpty()) {
            for (AtividadeDiaDto atividadeNome : dto.atividades()) {
                AtividadeEntity atividade = new AtividadeEntity();
                atividade.setAtividadeName(String.valueOf(atividadeNome));
                atividade.setHour(atividadeDiaDto.hour());
                atividade.setIsDone(false);
                atividade.setViagem(viagem);

                atividades.add(atividade);
            }

            atividadeRepository.saveAll(atividades);
            return atividades;


        }
        return atividades;
    }
}
