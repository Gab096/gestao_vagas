package br.com.gabriel.gestao_vagas.modules.candidate;

import br.com.gabriel.gestao_vagas.exceptions.JobNotFoundException;
import br.com.gabriel.gestao_vagas.exceptions.UserNotFoundException;
import br.com.gabriel.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.gabriel.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.gabriel.gestao_vagas.modules.candidate.useCases.ApplyJobCandidateUseCase;
import br.com.gabriel.gestao_vagas.modules.company.repositories.JobRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ApplyJobCandidateUseCaseTest {

 @InjectMocks
 private ApplyJobCandidateUseCase applyJobCandidateUseCase;

 @Mock
 private JobRepository jobRepository;
 @Mock
 private CandidateRepository candidateRepository;
 @Test
 @DisplayName("Shoud not be able to apply job with candidate not found")
 public void should_not_be_able_to_apply_job_with_candidate_not_found(){
    try {
     applyJobCandidateUseCase.execute(null ,null);
    }catch (Exception e){
        assertThat(e).isInstanceOf(UserNotFoundException.class);
    }
 }

 @Test
 public void should_not_be_able_to_apply_job_with_job_not_found(){
  var idCandidate = UUID.randomUUID();
  var candidate = new CandidateEntity();
  candidate.setId(idCandidate);

  when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));
  try {
   applyJobCandidateUseCase.execute(idCandidate ,null);
  }catch (Exception e){
   assertThat(e).isInstanceOf(JobNotFoundException.class);
  }

 }
}
