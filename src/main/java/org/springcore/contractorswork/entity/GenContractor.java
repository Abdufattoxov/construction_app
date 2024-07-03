package org.springcore.contractorswork.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenContractor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean isDone;
    private String role;

    @OneToMany(
            mappedBy = "genContractor",
            cascade = CascadeType.ALL
    )
    private List<SubContractor> subContractors = new ArrayList<>();

}
