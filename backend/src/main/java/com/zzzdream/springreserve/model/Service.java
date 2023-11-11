package com.zzzdream.springreserve.model;


import javax.persistence.*;

@Entity
@Table(name = "service")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "service_id_seq")
    @SequenceGenerator(name = "service_id_seq", sequenceName = "service_id_seq", allocationSize = 1)
    private Integer id;
    private String name;
    private String description;
    private String image;
    private Double price;

    public Service() {
    }

    public Service(Integer id, String name, String description, String image, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
    }
}
