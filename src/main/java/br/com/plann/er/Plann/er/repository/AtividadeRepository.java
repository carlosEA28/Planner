package br.com.plann.er.Plann.er.repository;

import br.com.plann.er.Plann.er.entity.AtividadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AtividadeRepository extends JpaRepository<AtividadeEntity, UUID> {
}
