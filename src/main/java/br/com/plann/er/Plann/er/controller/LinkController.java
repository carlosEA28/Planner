package br.com.plann.er.Plann.er.controller;

import br.com.plann.er.Plann.er.dto.LinkDto;
import br.com.plann.er.Plann.er.entity.LinkEntity;
import br.com.plann.er.Plann.er.service.LinkService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping("/create/{viagemId}")
    public ResponseEntity<LinkEntity> createLink(@Valid @RequestBody LinkDto dto, @PathVariable String viagemId) {
        var link = linkService.addLink(dto, viagemId);

        return ResponseEntity.ok().body(link);
    }
}
