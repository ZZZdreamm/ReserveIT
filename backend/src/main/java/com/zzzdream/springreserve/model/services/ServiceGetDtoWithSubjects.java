package com.zzzdream.springreserve.model.services;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToMany;
import java.util.Collection;

@Getter
@Setter
public class ServiceGetDtoWithSubjects extends ServiceGetDto{
    public Collection<SubjectGetDto> subjects;
    public ServiceGetDtoWithSubjects() {
        super();
    }
}
