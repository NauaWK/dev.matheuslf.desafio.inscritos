
package dev.matheuslf.desafio.inscritos.Repository;

import dev.matheuslf.desafio.inscritos.Model.Task;
import dev.matheuslf.desafio.inscritos.Utils.Enum.TaskPriority;
import dev.matheuslf.desafio.inscritos.Utils.Enum.TaskStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
    public boolean existsByTitle(String title);
    
    //consulta JPQL personalizada com base nos filtros opcionais
    @Query("""
    SELECT t FROM Task t
    WHERE (:status IS NULL OR t.status = :status)
      AND (:priority IS NULL OR t.priority = :priority)
      AND (:projectId IS NULL OR t.project.id = :projectId)
    """)
    List<Task> findTasksByFilters(
        @Param("status") TaskStatus status,
        @Param("priority") TaskPriority priority,
        @Param("projectId") Long projectId
    );


    
}
