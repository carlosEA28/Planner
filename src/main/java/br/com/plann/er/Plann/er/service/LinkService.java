package br.com.plann.er.Plann.er.service;

import br.com.plann.er.Plann.er.dto.LinkDto;
import br.com.plann.er.Plann.er.dto.LinkResponseDto;
import br.com.plann.er.Plann.er.entity.LinkEntity;
import br.com.plann.er.Plann.er.repository.LinkRepository;
import br.com.plann.er.Plann.er.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Autowired
    private ViagemRepository viagemRepository;

    public LinkEntity addLink(LinkDto dto, String viagemId) {

        var viagemExist = viagemRepository.findById(UUID.fromString(viagemId)).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem não encontrada"));

        var link = new LinkEntity();
        link.setName(dto.name());
        link.setLink(dto.link());
        link.setViagem(viagemExist);

        return linkRepository.save(link);
    }

    public List<LinkResponseDto> getLinks(String viagemId) {
        var viagem = viagemRepository.findById(UUID.fromString(viagemId))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Viagem não encontrada"));

        var links = linkRepository.findByViagem(viagem);

        if (links.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Você não possui links cadastrados para esta viagem");
        }

        return links.stream()
                .map(link -> new LinkResponseDto(
                        link.getName(),
                        link.getLink()
                ))
                .toList();
    }

}

