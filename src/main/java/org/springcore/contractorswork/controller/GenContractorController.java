
package org.springcore.contractorswork.controller;

import lombok.RequiredArgsConstructor;
import org.springcore.contractorswork.entity.SubContractor;
import org.springcore.contractorswork.service.SubContractorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/")
public class GenContractorController {
    private final SubContractorService subContractorService;

    @GetMapping("/genContractor")
    public String genContractorPage(Model model) {
        model.addAttribute("subContractor", new SubContractor());
        return "gen_contractor";
    }
    @PostMapping("/genContractor/create_sub")
    public String createSubContractor(@ModelAttribute SubContractor subContractor) {
        subContractorService.save(subContractor);
        return "redirect:/genContractor";
    }

}





/*package org.springcore.contractorswork.controller;

import lombok.RequiredArgsConstructor;
import org.springcore.contractorswork.entity.GenContractor;
import org.springcore.contractorswork.entity.SubContractor;
import org.springcore.contractorswork.service.GenContractorService;
import org.springcore.contractorswork.service.SubContractorService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/genContractor")

public class GenContractorController {
    private final SubContractorService subContractorService;
    private final GenContractorService genContractorService;

    @PostMapping("/create_sub")
    public ResponseEntity<SubContractor> createSubContractor(@RequestBody SubContractor subContractor){
        return ResponseEntity.ok(subContractorService.save(subContractor));
    }

    @PostMapping("/")
    public ResponseEntity create(  ){
        return ResponseEntity.ok(genContractorService.save());
    }
}*/
