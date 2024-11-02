package br.com.gabriel.gestao_vagas.modules.company.controllers;

import br.com.gabriel.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.gabriel.gestao_vagas.modules.company.entities.JobEntity;
import br.com.gabriel.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/company/jobs")
public class JobController {

    @Autowired
    private CreateJobUseCase createJobUseCase;

    @PostMapping("/")
    private JobEntity create(@Valid @RequestBody CreateJobDTO createJobDTO, HttpServletRequest request) {

        //jobEntity.setCompanyId( UUID.fromString(request.getAttribute("company_id").toString()));

        var jobEntity = JobEntity.builder()
                .benefits(createJobDTO.getBenefits())
                .companyId(UUID.fromString(request.getAttribute("company_id").toString()))
                .level(createJobDTO.getLevel())
                .description(createJobDTO.getDescription())
                .build()
        ;

        return this.createJobUseCase.execute(jobEntity);
    }


}
