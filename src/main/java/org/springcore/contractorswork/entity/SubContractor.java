package org.springcore.contractorswork.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubContractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isDone;
    private final String role = "SUBCONTRACTOR";

    @ManyToOne
    @JoinColumn(
            name = "gen_contractor_id"
    )
    private GenContractor genContractor;

    @OneToMany(
            mappedBy = "subContractor",
            cascade = CascadeType.ALL
    )
    @JsonManagedReference
    private List<ProjectManager> projectManagers = new ArrayList<>();
}


