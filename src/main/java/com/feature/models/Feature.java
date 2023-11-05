package com.feature.models;

import jakarta.persistence.*;

@Entity
@Table(name="feature")
public class Feature {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SQ_feature_id"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column
    private String name;

    public Feature(String name){
        this.name = name;
    }

    public Feature() {}

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
}
