
package dev.matheuslf.desafio.inscritos;

import dev.matheuslf.desafio.inscritos.DTO.ProjectDTOs.ProjectRequestDTO;
import dev.matheuslf.desafio.inscritos.Model.Project;
import dev.matheuslf.desafio.inscritos.Utils.Mappers.ProjectMappers;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MappersTest {
    
    private final ProjectMappers projectMappers = new ProjectMappers();
    
    @Test
    void mustConvertDtoToProject(){
        
        ProjectRequestDTO dto = new ProjectRequestDTO(
                "Projeto A", 
                "Descrição",  
                LocalDate.of(2025, 10, 20) //endDate
        );
        
        Project project = projectMappers.toProject(dto);
        
        assertEquals("Projeto A", project.getName());
        assertEquals("Descrição", project.getDescription());
        assertEquals(LocalDate.of(2025, 10, 20), project.getEndDate());       
    }   
    
}
