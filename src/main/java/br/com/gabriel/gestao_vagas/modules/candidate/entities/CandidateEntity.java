package br.com.gabriel.gestao_vagas.modules.candidate.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import jakarta.validation.constraints.Email;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDateTime;
import java.util.UUID;
@Data
@Entity(name = "candidate")
public class CandidateEntity {

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

    private String curriculum;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
