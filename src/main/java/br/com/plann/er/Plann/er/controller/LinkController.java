package br.com.plann.er.Plann.er.controller;

import br.com.plann.er.Plann.er.dto.LinkDto;
import br.com.plann.er.Plann.er.entity.LinkEntity;
import br.com.plann.er.Plann.er.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/link")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @PostMapping("/create")
    public ResponseEntity<LinkEntity> createLink(LinkDto dto, @RequestBody String viagemId) {
        var link = linkService.addLink(dto, viagemId);

        return ResponseEntity.ok().body(link);
    }
}
