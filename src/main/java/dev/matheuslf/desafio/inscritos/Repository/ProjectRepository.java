
package dev.matheuslf.desafio.inscritos.Repository;

import dev.matheuslf.desafio.inscritos.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    public boolean existsByName(String name);
    
}
