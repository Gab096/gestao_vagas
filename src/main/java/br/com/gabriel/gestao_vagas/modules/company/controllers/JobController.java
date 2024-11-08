package br.com.gabriel.gestao_vagas.modules.company.controllers;
import br.com.gabriel.gestao_vagas.modules.company.dto.CreateJobDTO;
import br.com.gabriel.gestao_vagas.modules.company.entities.JobEntity;
import br.com.gabriel.gestao_vagas.modules.company.useCases.CreateJobUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('COMPANY')")
    @Tag(name = "Vagas" , description = "Informações das vagas")
    @Operation(summary = "Cadastro de vagas" , description = "essa função é responsavel por cadastrar as vagas dentro da empresa")
    @ApiResponses(
            @ApiResponse(
                    responseCode = "200",
                    content = {
                        @Content(schema = @Schema(implementation = JobEntity.class))
                    }
            )
    )
    @SecurityRequirement(name = "jwt_auth")
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
