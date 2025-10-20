
package dev.matheuslf.desafio.inscritos.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "tb_projects")
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    
    private String description;
    
    
    @OneToMany(mappedBy = "project_id")
    private List<Task> tasks;
    
    
    @PrePersist
    public void aoCriar() {
        this.startDate = LocalDate.now();
    } 
    
    private LocalDate startDate;
    
    private LocalDate endDate;

    public Project() {}
    
    public Project(String name, String description, LocalDate endDate) {
        this.name = name;
        this.description = description;
        this.endDate = endDate;
    }
    
    public Long getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }  
    
}
