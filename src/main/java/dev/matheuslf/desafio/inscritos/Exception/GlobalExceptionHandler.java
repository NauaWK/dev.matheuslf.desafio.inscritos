
package dev.matheuslf.desafio.inscritos.Exception;

import dev.matheuslf.desafio.inscritos.Exception.CustomExceptions.InvalidEnumException;
import dev.matheuslf.desafio.inscritos.Exception.CustomExceptions.ObjectAlreadyExistsException;
import dev.matheuslf.desafio.inscritos.Exception.CustomExceptions.ProjectNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex){
        
        //HashMap conténdo pares: campo com erro, mensagem do erro
        Map<String, Object> errors = new HashMap<>();        
        errors.put("status", "400");
        errors.put("timestamp", LocalDateTime.now().withNano(0));
        errors.put("error", "1 ou mais campos inválidos");
        
        //selecionando todos os campos com erros da exceção e inserindo no HashMap "errors"
        ex.getBindingResult().getFieldErrors().forEach(error -> {
        errors.put(error.getField(), error.getDefaultMessage());            
        });             
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
        
    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleObjectAlreadyExistsException (ObjectAlreadyExistsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("400", ex.getMessage()));
    }
    
    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProjectNotFoundException (ProjectNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("404", ex.getMessage()));
    }
    
    @ExceptionHandler(InvalidEnumException.class)
     public ResponseEntity<ErrorResponse> handleInvalidEnumException (InvalidEnumException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("400", ex.getMessage()));
    }
       
}
