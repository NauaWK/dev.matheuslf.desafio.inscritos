
package dev.matheuslf.desafio.inscritos.Utils.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.matheuslf.desafio.inscritos.Exception.CustomExceptions.InvalidEnumException;

public enum TaskStatus {
    
    TODO, DOING, DONE;
    
    @JsonCreator
    public static TaskStatus from(String status){
        try {
            return TaskStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumException("Status inválido: " + status);
        }                       
    }
      
}
