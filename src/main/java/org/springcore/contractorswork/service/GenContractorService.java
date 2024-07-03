package org.springcore.contractorswork.service;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springcore.contractorswork.entity.GenContractor;
import org.springcore.contractorswork.repository.GenContractorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class GenContractorService {
    private final GenContractorRepository genContractorRepository;
    public Object save() {
        return genContractorRepository.save(
                GenContractor.builder()
                        .name("Shakhzod")
                        .isDone(false)
                        .build()
        );
    }
}
