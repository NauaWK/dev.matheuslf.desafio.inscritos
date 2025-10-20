
package dev.matheuslf.desafio.inscritos.Controller;

import dev.matheuslf.desafio.inscritos.DTO.ProjectDTOs.ProjectRequestDTO;
import dev.matheuslf.desafio.inscritos.DTO.ProjectDTOs.ProjectResponseDTO;
import dev.matheuslf.desafio.inscritos.Exception.CustomExceptions.ObjectAlreadyExistsException;
import dev.matheuslf.desafio.inscritos.Services.ProjectServices;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class ProjectController {
        
    private final ProjectServices projectServices;

    public ProjectController(ProjectServices projectServices) {
        this.projectServices = projectServices;
    }
     
    @PostMapping("/projects")
    public ResponseEntity<ProjectResponseDTO> addProject(@Valid @RequestBody ProjectRequestDTO request) {
        ProjectResponseDTO dto = projectServices.addProject(request);  
        return ResponseEntity.ok(dto);       
    }
    
    
    
}
