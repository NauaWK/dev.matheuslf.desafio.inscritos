
package dev.matheuslf.desafio.inscritos.Utils.Mappers;

import dev.matheuslf.desafio.inscritos.DTO.TaskDTOs.TaskRequestDTO;
import dev.matheuslf.desafio.inscritos.DTO.TaskDTOs.TaskResponseDTO;
import dev.matheuslf.desafio.inscritos.Model.Project;
import dev.matheuslf.desafio.inscritos.Model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMappers {
     
    public Task toTask(TaskRequestDTO taskRequest, Project project){
        return new Task(
            taskRequest.title(), 
            taskRequest.description(), 
            taskRequest.status(),
            taskRequest.priority(),
            taskRequest.dueDate(),
            project
        );    
    }  
    
    public TaskResponseDTO toDto(Task task){
        return new TaskResponseDTO(
            task.getId(),
            task.getTitle(), 
            task.getDescription(), 
            task.getStatus(),
            task.getPriority(),
            task.getDueDate(),
            task.getProject().getId()
        );      
    }
    
}
