package br.com.gabriel.gestao_vagas.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
 @Bean
 public OpenAPI openAPi(){

  return  new OpenAPI()
    .info(new Info().title("Gestão de vagas").description("API responsável pela gestão de vagas").version("1"))
    // se for pra adicionar auth a todas as rotas ::
    //.addSecurityItem(new SecurityRequirement().addList("Bearer Authentication"))
    //.components(new Components().addSecuritySchemes("Bearer Authentication" , createSecurityScheme()))
    .schemaRequirement("jwt_auth", createSecurityScheme())
    ;

 }

 private SecurityScheme createSecurityScheme() {
   return new SecurityScheme().name("jwt_auth").scheme("bearer").type(SecurityScheme.Type.HTTP).bearerFormat("JWT").in(SecurityScheme.In.HEADER);
 }

}
