
package dev.matheuslf.desafio.inscritos.DTO.ProjectDTOs;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record ProjectRequestDTO(
        
    @NotBlank(message = "Nome do projeto é obrigatório")
    @Size(min = 3, max = 100, message = "Nome precisa ter entre 3 a 100 carácteres")
    String name,

    String description,
    
    @FutureOrPresent(message = "A data de vencimento não pode estar no passado")
    LocalDate endDate

    ) {}
