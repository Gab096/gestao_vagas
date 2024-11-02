package br.com.gabriel.gestao_vagas.modules.company.useCases;

import br.com.gabriel.gestao_vagas.exceptions.UserFoundException;
import br.com.gabriel.gestao_vagas.modules.company.entities.CompanyEntity;
import br.com.gabriel.gestao_vagas.modules.company.repositories.CompanyRespository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateCompanyUseCase {
    @Autowired
    private CompanyRespository companyRespository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public CompanyEntity execute(CompanyEntity companyEntity){
        companyRespository
            .findByUsernameOrEmail(companyEntity.getUsername(),companyEntity.getEmail())
            .ifPresent((company)->{

            throw new UserFoundException();

        });

        var password = passwordEncoder.encode(companyEntity.getPassword());
        companyEntity.setPassword(passwordEncoder.encode(companyEntity.getPassword()));

    return companyRespository.save(companyEntity);
    }

}

