
package dev.matheuslf.desafio.inscritos.Controller;

import dev.matheuslf.desafio.inscritos.DTO.TaskDTOs.TaskRequestDTO;
import dev.matheuslf.desafio.inscritos.DTO.TaskDTOs.TaskResponseDTO;
import dev.matheuslf.desafio.inscritos.Services.TaskServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api")
public class TaskController {
    
    private final TaskServices taskServices;

    public TaskController(TaskServices taskServices) {
        this.taskServices = taskServices;
    }
      
    @PostMapping
    public ResponseEntity<TaskResponseDTO> addTask (@Valid @RequestBody TaskRequestDTO request){     
        TaskResponseDTO dto = taskServices.addTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);     
    }
    
    
    
}
