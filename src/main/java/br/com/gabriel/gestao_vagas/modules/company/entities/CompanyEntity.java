package br.com.gabriel.gestao_vagas.modules.company.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "company")
@Data
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Pattern(regexp = "\\S+" , message = " O campo [username] não deve conter espaço")
    private String username;

    @Length(min = 10 , max = 100, message = "A senha deve ser entre 10 e 15 caracteres")
    private String password;
    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

    private String website;

    private String description;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
