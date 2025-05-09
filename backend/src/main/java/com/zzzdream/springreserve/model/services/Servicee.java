package com.zzzdream.springreserve.model.services;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "service")
@Getter
@Setter
public class Servicee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_id_seq")
    @SequenceGenerator(name = "service_id_seq", sequenceName = "service_id_seq", allocationSize = 1)
    @JsonProperty
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    private String description;
    private String image;
    @ManyToMany(mappedBy = "services")
    private Collection<Subject> subjects;
    public Servicee() {
    }
    public Servicee(ServiceCreateDto createServiceDto){
        this.name = createServiceDto.name;
        this.price = createServiceDto.price;
        this.description = createServiceDto.description;
        this.image = createServiceDto.image;
    }

    public Servicee(String name, Double price, String description){
        this.name = name;
        this.price = price;
        this.description = description;
    }
    public Servicee(String name, Double price, String description, String image){
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }
    public void setSubjects(Collection<Subject> subjects) {
        this.subjects = subjects;
    }
    @JsonGetter("subjects")
    public Collection<Subject> getSubjects() {
        return subjects;
    }

}
