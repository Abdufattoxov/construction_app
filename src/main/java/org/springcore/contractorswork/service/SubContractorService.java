package org.springcore.contractorswork.service;

import lombok.RequiredArgsConstructor;
import org.springcore.contractorswork.entity.ProjectManager;
import org.springcore.contractorswork.entity.SubContractor;
import org.springcore.contractorswork.entity.Task;
import org.springcore.contractorswork.repository.ProjectManagerRepository;
import org.springcore.contractorswork.repository.SubContractorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubContractorService {
    private final SubContractorRepository subContractorRepository;
    private final ProjectManagerRepository projectManagerRepository;


    public SubContractor save(SubContractor contractor) {
        return subContractorRepository.save(contractor);
    }


    public SubContractor findById(Long scId) {
        return subContractorRepository.findById(scId).get();
    }

    public ProjectManager findPM(Long pmId) {
        ProjectManager projectManager = projectManagerRepository.findById(pmId).get();
        return projectManager;
    }

    public void putDone(ProjectManager pm) {
        pm.setDone(true);
        projectManagerRepository.save(pm);
    }
}
