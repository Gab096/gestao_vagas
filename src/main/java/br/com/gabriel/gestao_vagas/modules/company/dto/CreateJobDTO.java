package br.com.gabriel.gestao_vagas.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobDTO {

    private String benefits;
    private String level;
    private String description;
}