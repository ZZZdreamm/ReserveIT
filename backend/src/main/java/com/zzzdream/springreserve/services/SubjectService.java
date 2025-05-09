package com.zzzdream.springreserve.services;

import com.zzzdream.springreserve.mappers.SubjectMapper;
import com.zzzdream.springreserve.model.services.SubjectCreateDto;
import com.zzzdream.springreserve.model.services.Subject;
import com.zzzdream.springreserve.model.services.SubjectGetDto;
import com.zzzdream.springreserve.repository.SubjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private  final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    public SubjectService(SubjectRepository subjectRepository, SubjectMapper subjectMapper){
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }
    public ResponseEntity<?> createSubject(SubjectCreateDto subjectCreateDto){
        if(subjectRepository.existsByName(subjectCreateDto.name)){
            return ResponseEntity.badRequest().body("Subject with this name already exists");
        }
        Subject subject = new Subject(subjectCreateDto);
        subjectRepository.save(subject);
        return ResponseEntity.ok("Subject have been created successfully");
    }

    public List<? extends SubjectGetDto> getAllSubjects(boolean withServices){
        List<Subject> subjects = subjectRepository.findAll();
        if(withServices){
            return subjects.stream().map(subjectMapper::mapToGetDtoWithServices).collect(java.util.stream.Collectors.toList());
        }else{
            return subjects.stream().map(subjectMapper::mapToGetDto).collect(java.util.stream.Collectors.toList());
        }
    }
}
