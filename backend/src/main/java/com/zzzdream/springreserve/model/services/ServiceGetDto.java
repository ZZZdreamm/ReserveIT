package com.zzzdream.springreserve.model.services;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceGetDto {
    private Integer id;
    private String name;
    private Double price;
    private String description;
    private String image;
    public ServiceGetDto() {
    }
    public ServiceGetDto(Integer id, String name, Double price, String description, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
    }

}
