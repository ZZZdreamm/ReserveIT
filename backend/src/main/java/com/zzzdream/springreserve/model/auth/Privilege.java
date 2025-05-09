package com.zzzdream.springreserve.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private PrivilegeType privilegeType;
    @JsonManagedReference
    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
    public Privilege(PrivilegeType privilegeType) {
        this.privilegeType = privilegeType;
    }
    public Privilege() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @JsonIgnore
    public PrivilegeType getPrivilegeType() {
        return privilegeType;
    }
    public Collection<Role> getRoles() {
        return roles;
    }
}