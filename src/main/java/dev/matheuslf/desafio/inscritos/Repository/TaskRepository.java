
package dev.matheuslf.desafio.inscritos.Repository;

import dev.matheuslf.desafio.inscritos.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
