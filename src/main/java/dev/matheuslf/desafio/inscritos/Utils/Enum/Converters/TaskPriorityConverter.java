
package dev.matheuslf.desafio.inscritos.Utils.Enum.Converters;

import dev.matheuslf.desafio.inscritos.Exception.CustomExceptions.InvalidEnumException;
import dev.matheuslf.desafio.inscritos.Utils.Enum.TaskPriority;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskPriorityConverter implements Converter<String, TaskPriority> {

    @Override
    public TaskPriority convert(String source) {
        try {
            return TaskPriority.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumException("Prioridade inválida: " + source);
        }
    }
}

