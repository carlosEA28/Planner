package br.com.plann.er.Plann.er.repository;

import br.com.plann.er.Plann.er.entity.LinkEntity;
import br.com.plann.er.Plann.er.entity.ViagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LinkRepository extends JpaRepository<LinkEntity, UUID> {
    Optional<LinkEntity> findByName(String name);

    List<LinkEntity> findByViagem(ViagemEntity viagem);
}
