
package dev.matheuslf.desafio.inscritos.UnitaryTests.ServicesTests;

import dev.matheuslf.desafio.inscritos.DTO.TaskDTOs.TaskRequestDTO;
import dev.matheuslf.desafio.inscritos.DTO.TaskDTOs.TaskResponseDTO;
import dev.matheuslf.desafio.inscritos.Exception.CustomExceptions.ObjectAlreadyExistsException;
import dev.matheuslf.desafio.inscritos.Exception.CustomExceptions.ProjectNotFoundException;
import dev.matheuslf.desafio.inscritos.Model.Project;
import dev.matheuslf.desafio.inscritos.Model.Task;
import dev.matheuslf.desafio.inscritos.Repository.ProjectRepository;
import dev.matheuslf.desafio.inscritos.Repository.TaskRepository;
import dev.matheuslf.desafio.inscritos.Services.TaskServices;
import dev.matheuslf.desafio.inscritos.Utils.Enum.TaskPriority;
import dev.matheuslf.desafio.inscritos.Utils.Enum.TaskStatus;
import dev.matheuslf.desafio.inscritos.Utils.Mappers.TaskMappers;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TaskServicesTests {
    
    @Mock
    private TaskRepository taskRepository;
    
    @Mock
    private ProjectRepository projectRepository;
    
    @Mock
    private TaskMappers taskMappers;
    
    @InjectMocks
    private TaskServices taskServices;
    
    @Test
    void addTask_shouldSucceed_whenValidRequest(){
        
        Project project = new Project("Projeto A", "Descrição", LocalDate.of(2025, 10, 20));
        project.setId(1L);
        
        TaskRequestDTO request = new TaskRequestDTO(
            "Task A", 
            "Descrição", 
            TaskStatus.TODO, 
            TaskPriority.LOW,
            LocalDate.of(2025, 10, 20),
            project.getId()
        );
        
        Task task = new Task(
            "Task A",
            "Descrição", 
            TaskStatus.TODO, 
            TaskPriority.LOW,
            LocalDate.of(2025, 10, 20),
            project        
        );
        task.setId(1L);
        
        TaskResponseDTO response = new TaskResponseDTO(
            1L,
            "Task A",
            "Descrição", 
            TaskStatus.TODO, 
            TaskPriority.LOW,
            LocalDate.of(2025, 10, 20),
            project.getId()
        );
        
        when(projectRepository.findById(request.projectId())).thenReturn(Optional.of(project));       
        when(taskRepository.existsByTitle("Task A")).thenReturn(false);
        when(taskRepository.save(task)).thenReturn(task);
        when(taskMappers.toTask(request, project)).thenReturn(task);
        when(taskMappers.toDto(task)).thenReturn(response);
        
        TaskResponseDTO result = taskServices.addTask(request);
        
        assertEquals(response.id(), result.id());        
              
    }
    
    @Test
    void addTask_shouldFail_whenTaskAlreadyExists(){
        
        TaskRequestDTO request = new TaskRequestDTO(
            "Task A", 
            "Descrição", 
            TaskStatus.TODO, 
            TaskPriority.LOW,
            LocalDate.of(2025, 10, 20),
            1L
        );
        
        when(taskRepository.existsByTitle("Task A")).thenReturn(true);

        assertThrows(ObjectAlreadyExistsException.class, () -> taskServices.addTask(request));
        verify(taskRepository).existsByTitle("Task A");
    }
    
    @Test
    void addTask_shouldFail_whenProjectDoesNotExist(){
        
        Project project = new Project("Projeto A", "Descrição", LocalDate.of(2025, 10, 20));
        project.setId(1L);
               
        TaskRequestDTO request = new TaskRequestDTO(
            "Task A", 
            "Descrição", 
            TaskStatus.TODO, 
            TaskPriority.LOW,
            LocalDate.of(2025, 10, 20),
            project.getId()
        );
               
        when(projectRepository.findById(request.projectId())).thenReturn(Optional.empty());

        assertThrows(ProjectNotFoundException.class, () -> taskServices.addTask(request));  
        verify(projectRepository).findById(request.projectId());
    }  
     
    /*
    @Test
    void getTask_shouldSucceed_whenUsingValidFilters(){
        
        Project project = new Project("Projeto A", "Descrição", LocalDate.of(2025, 10, 20));
        project.setId(1L);      
        
        Task task = new Task(
            "Task A",
            "Descrição", 
            TaskStatus.TODO, 
            TaskPriority.HIGH,
            LocalDate.of(2025, 10, 20),
            project        
        );
        task.setId(1L);
        
        TaskResponseDTO response = new TaskResponseDTO(
            1L,
            "Task A",
            "Descrição", 
            TaskStatus.TODO, 
            TaskPriority.LOW,
            LocalDate.of(2025, 10, 20),
            project.getId()
        );      
        
        List<Task> tasks = List.of(task);
        List<TaskResponseDTO> responseDtos = List.of(response);
        
        when(taskRepository.findTasksByFilters("TODO", "HIGH", project.getId())).thenReturn(tasks);
                     
        assertEquals(responseDtos, taskServices.getTaskWithFilters("todo", "high", "1"));
        verify(taskRepository).findTasksByFilters("TODO", "HIGH", project.getId());   
        
    }*/
       
}
