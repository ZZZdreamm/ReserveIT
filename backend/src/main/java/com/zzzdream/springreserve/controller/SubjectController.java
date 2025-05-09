package com.zzzdream.springreserve.controller;

import com.zzzdream.springreserve.model.services.SubjectCreateDto;
import com.zzzdream.springreserve.model.services.Subject;
import com.zzzdream.springreserve.model.services.SubjectGetDto;
import com.zzzdream.springreserve.services.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {
    private final SubjectService subjectService;
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }
    @PostMapping("/create")
    public ResponseEntity<?> createSubject(@Valid @RequestBody SubjectCreateDto subjectCreateDto){
        return subjectService.createSubject(subjectCreateDto);
    }

    @GetMapping("/all")
    public List<? extends SubjectGetDto> getAllSubjects(@RequestParam(required = false) boolean withServices){
        return  subjectService.getAllSubjects(withServices);
    }
}
