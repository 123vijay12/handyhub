package com.handyhub.user.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;   // e.g. "Plumbing", "Electrical", "Painting"

    @ManyToMany(mappedBy = "skills")
    private Set<WorkerProfile> workers;

    public Skill(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<WorkerProfile> getWorkers() {
        return workers;
    }

    public void setWorkers(Set<WorkerProfile> workers) {
        this.workers = workers;
    }
}
