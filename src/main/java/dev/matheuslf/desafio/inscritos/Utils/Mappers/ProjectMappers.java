
package dev.matheuslf.desafio.inscritos.Utils.Mappers;

import dev.matheuslf.desafio.inscritos.DTO.ProjectDTOs.ProjectRequestDTO;
import dev.matheuslf.desafio.inscritos.DTO.ProjectDTOs.ProjectResponseDTO;
import dev.matheuslf.desafio.inscritos.Model.Project;
import org.springframework.stereotype.Component;

@Component
public class ProjectMappers {
    
    public Project toProject(ProjectRequestDTO projectRequest){
        return new Project(
            projectRequest.name(), 
            projectRequest.description(), 
            projectRequest.endDate()
        );      
    }  
    
    public ProjectResponseDTO toDto(Project project){
        return new ProjectResponseDTO(
            project.getId(),
            project.getName(), 
            project.getDescription(), 
            project.getStartDate(),
            project.getEndDate()
        );      
    }
    
}
