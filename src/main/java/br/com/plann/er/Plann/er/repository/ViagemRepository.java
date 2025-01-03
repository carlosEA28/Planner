package br.com.plann.er.Plann.er.repository;

import br.com.plann.er.Plann.er.entity.ConvidadoEntity;
import br.com.plann.er.Plann.er.entity.ViagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ViagemRepository extends JpaRepository<ViagemEntity, UUID> {
    List<ConvidadoEntity> findByViagem(ViagemEntity viagem);

}
