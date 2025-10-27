
package dev.matheuslf.desafio.inscritos.Services;

import dev.matheuslf.desafio.inscritos.DTO.ProjectDTOs.ProjectRequestDTO;
import dev.matheuslf.desafio.inscritos.DTO.ProjectDTOs.ProjectResponseDTO;
import dev.matheuslf.desafio.inscritos.Exception.CustomExceptions.ObjectAlreadyExistsException;
import dev.matheuslf.desafio.inscritos.Model.Project;
import dev.matheuslf.desafio.inscritos.Repository.ProjectRepository;
import dev.matheuslf.desafio.inscritos.Utils.Mappers.ProjectMappers;
import java.util.List;
import java.util.stream.Collectors;
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
        if(projectRepository.existsByName(request.name())){
            throw new ObjectAlreadyExistsException("Projeto com o nome " + request.name() + " já existe.");         
        }  
        
        Project project = projectMappers.toProject(request);
        projectRepository.save(project);      
        return projectMappers.toDto(project);
    }
    
    public List<ProjectResponseDTO> getAllProjects(){
        List<Project> projectsList =  projectRepository.findAll();

        //convertendo cada Project em ProjectResponseDTO
        List<ProjectResponseDTO> responseDtos = projectsList.stream().map(project -> new ProjectResponseDTO(               
            project.getId(),
            project.getName(),
            project.getDescription(),
            project.getStartDate(),
            project.getEndDate())).collect(Collectors.toList());

        return responseDtos;
    }
    
}
