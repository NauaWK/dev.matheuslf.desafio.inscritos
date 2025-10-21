
package dev.matheuslf.desafio.inscritos.UnitaryTests.ServicesTests;

import dev.matheuslf.desafio.inscritos.DTO.ProjectDTOs.ProjectRequestDTO;
import dev.matheuslf.desafio.inscritos.DTO.ProjectDTOs.ProjectResponseDTO;
import dev.matheuslf.desafio.inscritos.Exception.CustomExceptions.ObjectAlreadyExistsException;
import dev.matheuslf.desafio.inscritos.Model.Project;
import dev.matheuslf.desafio.inscritos.Repository.ProjectRepository;
import dev.matheuslf.desafio.inscritos.Services.ProjectServices;
import dev.matheuslf.desafio.inscritos.Utils.Mappers.ProjectMappers;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ProjectServicesTests {
    
    
    @Mock
    private ProjectRepository projectRepository;
    
    @Mock
    private ProjectMappers projectMappers;
    
    @InjectMocks
    private ProjectServices projectServices;
    
    @Test
    void shouldAddProjectSuccessfully(){
        
        ProjectRequestDTO request = new ProjectRequestDTO("Projeto A", "Descrição", LocalDate.of(2025, 10, 20));
        
        Project project = new Project("Projeto A", "Descrição", LocalDate.of(2025, 10, 20));
        project.setId(1L);
        LocalDate today = LocalDate.now();
        project.setStartDate(today);
        
        ProjectResponseDTO response = new ProjectResponseDTO(
                1L,
                "Projeto A", 
                "Descrição", 
                today,
                LocalDate.of(2025, 10, 20));

        when(projectRepository.existsByName("Projeto A")).thenReturn(false);
        when(projectMappers.toProject(request)).thenReturn(project);
        when(projectRepository.save(project)).thenReturn(project);
        when(projectMappers.toDto(project)).thenReturn(response);

        ProjectResponseDTO result = projectServices.addProject(request);

        assertEquals("Projeto A", result.name());
        
    }
    
    @Test
    void shouldThrowExceptionIfProjectExists() {
        ProjectRequestDTO request = new ProjectRequestDTO("Projeto A", "Descrição", LocalDate.of(2025, 10, 20));
        when(projectRepository.existsByName("Projeto A")).thenReturn(true);

        assertThrows(ObjectAlreadyExistsException.class, () -> projectServices.addProject(request));
    }
       
}
