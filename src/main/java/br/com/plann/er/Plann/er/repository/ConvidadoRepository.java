package br.com.plann.er.Plann.er.repository;

import br.com.plann.er.Plann.er.entity.ConvidadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConvidadoRepository extends JpaRepository<ConvidadoEntity, UUID> {
    List<ConvidadoEntity> findByViagem_ViagemId(UUID viagemId);
}
