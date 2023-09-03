package com.example.photoBlog.photo;

import javax.persistence.*;
//import javax.persistence.Table;

// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.SequenceGenerator;

import javax.persistence.Id;

@Entity
public class Photo {
    // @Id @GeneratedValue Long Id;
    @Id
    @SequenceGenerator(
            name = "photo_sequence",
            sequenceName = "photo_sequence",
            allocationSize = 1    
    )
    
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "photo_sequence"
    )
    private Long id;
    private String name;
    private Integer rank;
    private String category;

    protected Photo() {}

    public Photo(Long id, String name, Integer rank, String category) {
        this.id = id;
        this.name = name;
        this.rank = rank;
        this.category = category;
    }

    public Photo(String name, Integer rank, String category) {
        this.name = name;
        this.rank = rank;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public Integer getRank() {
        return rank;
    }

    public String getCategory() {
        return category;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return "Photo{" + "id=" + id + ", name='" + name + '\'' + ", rank='" + rank + '\'' + ", category='" + category
                + '\'' + '}';
    }
}
