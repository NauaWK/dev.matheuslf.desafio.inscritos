
package dev.matheuslf.desafio.inscritos.DTO.ProjectDTOs;

import java.time.LocalDate;


public record ProjectResponseDTO(
        
        Long id,       
        String name,        
        String description,       
        LocalDate startDate,       
        LocalDate endDate      
        
        ) {}
