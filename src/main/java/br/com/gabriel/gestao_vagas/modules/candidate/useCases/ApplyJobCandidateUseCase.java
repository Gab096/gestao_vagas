package br.com.gabriel.gestao_vagas.modules.candidate.useCases;
import br.com.gabriel.gestao_vagas.exceptions.JobNotFoundException;
import br.com.gabriel.gestao_vagas.exceptions.UserNotFoundException;
import br.com.gabriel.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import br.com.gabriel.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.gabriel.gestao_vagas.modules.company.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ApplyJobCandidateUseCase {
 @Autowired
 private JobRepository jobRepository;
 @Autowired
 private ApplyJobRepository applyJobRepository;
 @Autowired
 private CandidateRepository candidateRepository;

  public void execute(UUID idCandidate , UUID idJob){
    candidateRepository.findById(idCandidate).orElseThrow(UserNotFoundException::new);

    jobRepository.findById(idJob).orElseThrow(JobNotFoundException::new);

  }
}
