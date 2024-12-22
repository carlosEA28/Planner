package br.com.plann.er.Plann.er.repository;

import br.com.plann.er.Plann.er.entity.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LinkRepository extends JpaRepository<LinkEntity, UUID> {
}
