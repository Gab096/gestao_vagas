package br.com.gabriel.gestao_vagas.modules.company.repositories;

import br.com.gabriel.gestao_vagas.modules.company.entities.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface JobRepository  extends JpaRepository<JobEntity, UUID> {
    List<JobEntity> findByDescriptionContainingIgnoreCase(String filter);
}
