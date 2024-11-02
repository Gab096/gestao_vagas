package br.com.gabriel.gestao_vagas.modules.candidate.useCases;

import br.com.gabriel.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.gabriel.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProfileCandidateUseCase {
    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTO execute(UUID  idCandidate){
        var candidate = candidateRepository.findById(idCandidate)
                .orElseThrow(()-> new UsernameNotFoundException("user not found"));

        return ProfileCandidateResponseDTO.builder()
                .email(candidate.getEmail())
                .username(candidate.getUsername())
                .id(candidate.getId())
                .email(candidate.getEmail())
                .name(candidate.getName())
                .build();

    }


}
