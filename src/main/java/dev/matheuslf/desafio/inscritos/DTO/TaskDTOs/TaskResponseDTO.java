
package dev.matheuslf.desafio.inscritos.DTO.TaskDTOs;

import dev.matheuslf.desafio.inscritos.Utils.Enum.TaskPriority;
import dev.matheuslf.desafio.inscritos.Utils.Enum.TaskStatus;
import java.time.LocalDate;

public record TaskResponseDTO(
        
        Long id,
        String title,
        String description,
        TaskStatus status,
        TaskPriority priority,
        LocalDate dueDate,
        Long projectId
           
        ) {}
