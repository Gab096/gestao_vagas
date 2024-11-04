package br.com.gabriel.gestao_vagas.modules.company.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateJobDTO {

    @Schema(example = "gympass,planode saúde" , requiredMode = Schema.RequiredMode.REQUIRED)
    private String benefits;
    @Schema(example = "júnior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;
    @Schema(example = "vaga para pessoa desenvolvedora junior", requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;
}
