package br.com.gabriel.gestao_vagas.modules.candidate.useCases;

import br.com.gabriel.gestao_vagas.exceptions.UserFoundException;
import br.com.gabriel.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.gabriel.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CreateCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CandidateEntity execute(@Valid @RequestBody CandidateEntity candidateEntity){
        this.candidateRepository
                .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(passwordEncoder.encode(candidateEntity.getPassword()));
        return this.candidateRepository.save(candidateEntity);
    }
}
