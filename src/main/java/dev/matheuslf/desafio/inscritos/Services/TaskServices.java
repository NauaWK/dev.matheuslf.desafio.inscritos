
package dev.matheuslf.desafio.inscritos.Services;

import dev.matheuslf.desafio.inscritos.DTO.TaskDTOs.TaskRequestDTO;
import dev.matheuslf.desafio.inscritos.DTO.TaskDTOs.TaskResponseDTO;
import dev.matheuslf.desafio.inscritos.Exception.CustomExceptions.ObjectAlreadyExistsException;
import dev.matheuslf.desafio.inscritos.Exception.CustomExceptions.ProjectNotFoundException;
import dev.matheuslf.desafio.inscritos.Model.Project;
import dev.matheuslf.desafio.inscritos.Model.Task;
import dev.matheuslf.desafio.inscritos.Repository.ProjectRepository;
import dev.matheuslf.desafio.inscritos.Repository.TaskRepository;
import dev.matheuslf.desafio.inscritos.Utils.Enum.TaskPriority;
import dev.matheuslf.desafio.inscritos.Utils.Enum.TaskStatus;
import dev.matheuslf.desafio.inscritos.Utils.Mappers.TaskMappers;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TaskServices {
    
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskMappers taskMappers;

    public TaskServices(
            TaskRepository taskRepository, 
            ProjectRepository projectRepository, 
            TaskMappers taskMappers) {
        
        this.taskRepository = taskRepository;
        this.taskMappers = taskMappers;
        this.projectRepository = projectRepository;
    }
    
    public TaskResponseDTO addTask(TaskRequestDTO request) {
        if (taskRepository.existsByTitle(request.title())) {
            throw new ObjectAlreadyExistsException("Task com o título " + request.title() + " já existe.");
        }

        Project project = projectRepository.findById(request.projectId())
            .orElseThrow(() -> new ProjectNotFoundException("Projeto com o ID " + request.projectId() + " não existe."));

        Task task = taskMappers.toTask(request, project);
        taskRepository.save(task);
        return taskMappers.toDto(task);
    }
    
    public List<TaskResponseDTO> getTaskWithFilters(TaskStatus status, TaskPriority priority, String projectIdString){
                           
        Long projectIdLong = null;       
        if(projectIdString != null){
            projectIdLong = Long.valueOf(projectIdString);           
            projectRepository.findById(projectIdLong)
                    .orElseThrow(() -> new ProjectNotFoundException("Projeto com o ID " + projectIdString + " não existe."));
        }
                                 
        List<Task> tasks = taskRepository.findTasksByFilters(status, priority, projectIdLong);
        
        List<TaskResponseDTO> responseDtos = tasks.stream().map(task -> new TaskResponseDTO(               
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getDueDate(),
                task.getProject().getId())).collect(Collectors.toList());

        return responseDtos;
    }
        
}
    
    
     
