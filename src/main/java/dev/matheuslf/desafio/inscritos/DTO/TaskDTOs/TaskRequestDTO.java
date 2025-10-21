
package dev.matheuslf.desafio.inscritos.DTO.TaskDTOs;

import dev.matheuslf.desafio.inscritos.Utils.Enum.TaskPriority;
import dev.matheuslf.desafio.inscritos.Utils.Enum.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public record TaskRequestDTO(
        
        
    @NotBlank(message = "Nome da task é obrigatório")
    @Size(min = 5, max = 150, message = "Nome da task precisa ter entre 5 a 150 carácteres")
    String title,
        
    @NotBlank(message = "Descrição da task é obrigatória")
    String description,
    
    @NotNull(message = "status da task é obrigatório")
    TaskStatus status,
    
    @NotNull(message = "grau de prioridade da task é obrigatório")
    TaskPriority priority,
    
    @NotNull(message = "data limite é obrigatória")
    @FutureOrPresent(message = "A data de vencimento não pode estar no passado")   
    LocalDate dueDate,
    
    @NotNull(message = "ID do projeto relacionado à esta task é obrigatório")
    Long projectId
                     
    ){}
