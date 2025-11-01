
package dev.matheuslf.desafio.inscritos.Utils.Enum.Converters;

import dev.matheuslf.desafio.inscritos.Exception.CustomExceptions.InvalidEnumException;
import dev.matheuslf.desafio.inscritos.Utils.Enum.TaskStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TaskStatusConverter implements Converter<String, TaskStatus> {

    @Override
    public TaskStatus convert(String source) {
        try {
            return TaskStatus.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumException("Status inválido: " + source);
        }
    }
}

