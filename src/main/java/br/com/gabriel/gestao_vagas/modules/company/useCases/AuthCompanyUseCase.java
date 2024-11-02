package br.com.gabriel.gestao_vagas.modules.company.useCases;

import br.com.gabriel.gestao_vagas.modules.company.dto.AuthCompanyDto;
import br.com.gabriel.gestao_vagas.modules.company.dto.AuthCompanyResponseDTO;
import br.com.gabriel.gestao_vagas.modules.company.repositories.CompanyRespository;
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
public class AuthCompanyUseCase {

    @Value("${security.token.secret}")
    private String secretKey;
    @Autowired
    private CompanyRespository companyRespository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    public AuthCompanyResponseDTO execute (AuthCompanyDto authCompanyDto) throws AuthenticationException {
        var company = companyRespository.findByUsername(authCompanyDto.getUsername()).orElseThrow(
            () -> new UsernameNotFoundException("Username/Password incorrect")
        );

        var passwordMatches = passwordEncoder.matches(authCompanyDto.getPassword() , company.getPassword());

        if(!passwordMatches){
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var expiresIn = Instant.now().plus(Duration.ofHours(2));

        var token = JWT.create()
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withIssuer("javagas")
                .withExpiresAt(expiresIn)
                .withClaim("roles" , Arrays.asList("COMPANY"))
                .withSubject(company.getId().toString())
                .sign(algorithm);

        var authCompanyResponseDTO = AuthCompanyResponseDTO.builder()
                .access_token(token)
                .expires_in(expiresIn.toEpochMilli())
                .build();

        return authCompanyResponseDTO ;

    }
}
