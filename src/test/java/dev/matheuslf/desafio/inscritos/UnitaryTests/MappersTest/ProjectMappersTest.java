
package dev.matheuslf.desafio.inscritos.UnitaryTests.MappersTest;

import dev.matheuslf.desafio.inscritos.DTO.ProjectDTOs.ProjectRequestDTO;
import dev.matheuslf.desafio.inscritos.DTO.ProjectDTOs.ProjectResponseDTO;
import dev.matheuslf.desafio.inscritos.Model.Project;
import dev.matheuslf.desafio.inscritos.Utils.Mappers.ProjectMappers;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class ProjectMappersTest {    
    
    private final ProjectMappers projectMappers = new ProjectMappers();
    
    @Test
    void shouldConvertDtoToProject() {
        ProjectRequestDTO dto = new ProjectRequestDTO(
            "Projeto A",
            "Descrição",
            LocalDate.of(2025, 10, 20)
        );

        Project project = projectMappers.toProject(dto);
        
        //simula comportamento de @PrePersist
        LocalDate today = LocalDate.now();
        project.setStartDate(today);

        assertNotNull(project);
        assertEquals("Projeto A", project.getName());
        assertEquals("Descrição", project.getDescription());
        assertEquals(today, project.getStartDate());
        assertEquals(LocalDate.of(2025, 10, 20), project.getEndDate());
    }
     
    @Test
    void shouldConvertProjectToDto(){
        
        Project project = new Project(
                "Projeto A",
                "Descrição",
                LocalDate.of(2025, 10, 20)  //endDate
        );      
        LocalDate today = LocalDate.now();
        project.setStartDate(today);
             
        ProjectResponseDTO dto = projectMappers.toDto(project);
        
        assertNotNull(dto);
        assertEquals("Projeto A", dto.name());
        assertEquals("Descrição", dto.description());
        assertEquals(today, dto.startDate());
        assertEquals(LocalDate.of(2025, 10, 20), dto.endDate());       
    }
       
}
