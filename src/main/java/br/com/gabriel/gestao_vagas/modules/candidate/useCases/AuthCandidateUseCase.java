package br.com.gabriel.gestao_vagas.modules.candidate.useCases;

import br.com.gabriel.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.gabriel.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.gabriel.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secretKey;
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException {
            var candidate = candidateRepository.findByUsername(authCandidateRequestDTO.username())
                    .orElseThrow(() -> new UsernameNotFoundException("Username/password incorrect"));
            var passwordMatches = passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

            if (!passwordMatches){
                throw new AuthenticationException();
            }

        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var token = JWT.create()
                .withExpiresAt(expiresIn)
                .withIssuer("javagas")
                .withClaim("roles", Arrays.asList("CANDIDATE"))
                .withSubject(candidate.getId().toString())
                .sign(algorithm);

        return AuthCandidateResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

    }
}
