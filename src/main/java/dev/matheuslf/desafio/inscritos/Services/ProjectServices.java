
package dev.matheuslf.desafio.inscritos.Services;

import dev.matheuslf.desafio.inscritos.DTO.ProjectDTOs.ProjectRequestDTO;
import dev.matheuslf.desafio.inscritos.DTO.ProjectDTOs.ProjectResponseDTO;
import dev.matheuslf.desafio.inscritos.Exception.CustomExceptions.ObjectAlreadyExistsException;
import dev.matheuslf.desafio.inscritos.Model.Project;
import dev.matheuslf.desafio.inscritos.Repository.ProjectRepository;
import dev.matheuslf.desafio.inscritos.Utils.Mappers.ProjectMappers;
import org.springframework.stereotype.Service;


@Service
public class ProjectServices {
    
    private final ProjectMappers projectMappers;
    private final ProjectRepository projectRepository;

    public ProjectServices(ProjectMappers projectMappers, ProjectRepository projectRepository) {
        this.projectMappers = projectMappers;
        this.projectRepository = projectRepository;
    }
    
    public ProjectResponseDTO addProject(ProjectRequestDTO request) {
        if(!projectRepository.existsByName(request.name())){
            Project project = projectMappers.toProject(request);
            projectRepository.save(project);      
            ProjectResponseDTO dto = projectMappers.toDto(project);
            return dto;            
        }    
        else{
            throw new ObjectAlreadyExistsException("Projeto com o nome " + request.name() + " já existe.");
        }     
    }
    
}
