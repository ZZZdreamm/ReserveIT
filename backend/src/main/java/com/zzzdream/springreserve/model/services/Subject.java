package com.zzzdream.springreserve.model.services;


import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "subject")
@Getter
@Setter
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_id_seq")
    @SequenceGenerator(name = "subject_id_seq", sequenceName = "subject_id_seq", allocationSize = 1)
    private Integer id;
    @Column(nullable = false)
    private String name;
    private String image;
    @ManyToMany()
    @JoinTable(
            name = "subject_services",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private Collection<Servicee> services;
    public Subject() {
    }
    public Subject(String name) {
        this.name = name;
    }
    public Subject(String name, String image) {
        this.name = name;
        this.image = image;
    }
    public Subject(SubjectCreateDto subjectCreateDto){
        this.name = subjectCreateDto.name;
        this.image = subjectCreateDto.image;
    }
    public void setServices(Collection<Servicee> services) {
        this.services = services;
    }
    public void addServices(Collection<Servicee> services) {
        this.services.addAll(services);
    }
    @JsonGetter("services")
    public Collection<Servicee> getServices() {
        return services;
    }

}
