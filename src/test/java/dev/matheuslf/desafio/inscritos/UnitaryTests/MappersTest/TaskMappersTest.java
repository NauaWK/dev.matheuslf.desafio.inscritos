
package dev.matheuslf.desafio.inscritos.UnitaryTests.MappersTest;

import dev.matheuslf.desafio.inscritos.DTO.TaskDTOs.TaskRequestDTO;
import dev.matheuslf.desafio.inscritos.DTO.TaskDTOs.TaskResponseDTO;
import dev.matheuslf.desafio.inscritos.Model.Project;
import dev.matheuslf.desafio.inscritos.Model.Task;
import dev.matheuslf.desafio.inscritos.Utils.Enum.TaskPriority;
import dev.matheuslf.desafio.inscritos.Utils.Enum.TaskStatus;
import dev.matheuslf.desafio.inscritos.Utils.Mappers.TaskMappers;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;

public class TaskMappersTest {
    
    private final TaskMappers taskMappers = new TaskMappers();   
    
    @Test
    void shouldConvertDtoToTask(){
        
        Project project = new Project(
            "Projeto A",
            "Descrição",
            LocalDate.of(2025, 10, 20)  
        );        
        project.setId(1L);
        LocalDate today = LocalDate.now();
        project.setStartDate(today);
                    
        TaskRequestDTO dto = new TaskRequestDTO(
                "Task A", 
                "Descrição",  
                TaskStatus.TODO,
                TaskPriority.LOW,
                LocalDate.of(2025, 10, 20),
                1L
        );
        
        Task task = taskMappers.toTask(dto, project);
        
        assertNotNull(task);
        assertEquals("Task A", task.getTitle());
        assertEquals("Descrição", task.getDescription());
        assertEquals(TaskStatus.TODO, task.getStatus());
        assertEquals(TaskPriority.LOW, task.getPriority());
        assertEquals(LocalDate.of(2025, 10, 20), task.getDueDate());
        assertEquals(1L, task.getProject().getId());
        
    }
    
    @Test
    void shouldCovertTaskToDto(){
        
        Project project = new Project(
            "Projeto A",
            "Descrição",
            LocalDate.of(2025, 10, 20)  
        );        
        project.setId(1L);
        LocalDate today = LocalDate.now();
        project.setStartDate(today);
        
        Task task = new Task(       
            "Task A",
            "Descrição",
            TaskStatus.TODO,
            TaskPriority.LOW,
            LocalDate.of(2025, 10, 20),
            project
        );
        
        TaskResponseDTO dto = taskMappers.toDto(task);
               
        assertNotNull(dto);
        assertEquals("Task A", dto.title());
        assertEquals("Descrição", dto.description());
        assertEquals(TaskStatus.TODO, dto.status());
        assertEquals(TaskPriority.LOW, dto.priority());
        assertEquals(LocalDate.of(2025, 10, 20), dto.dueDate());
        assertEquals(1L, dto.projectId());            
    }
    
}
