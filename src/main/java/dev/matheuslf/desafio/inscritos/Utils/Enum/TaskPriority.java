
package dev.matheuslf.desafio.inscritos.Utils.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.matheuslf.desafio.inscritos.Exception.CustomExceptions.InvalidEnumException;

public enum TaskPriority {
    
    LOW, MEDIUM, HIGH;
        
    @JsonCreator
    public static TaskPriority from(String priority){
        try {
            return TaskPriority.valueOf(priority.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumException("Prioridade inválida: " + priority);
        }                       
    }
    
}
