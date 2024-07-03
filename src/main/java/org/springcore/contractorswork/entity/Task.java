package org.springcore.contractorswork.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer priority;
    private boolean isDone;

    @ManyToOne()
    @JoinColumn(
            name = "project_manager_id"
    )
    @JsonBackReference
    private ProjectManager projectManager;

}
