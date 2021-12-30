package com.domain.models.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_categories")
public class Category extends BaseEntity<String> implements Serializable{   //extends BaseEntity for auditing purpose
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //setting Id as Primary Key
    private Long id;
    
    @Column(length = 100, nullable = false, unique=true) //setting category_name NOT NULL & UNIQUE
    private String categoryName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String category_name) {
        this.categoryName = category_name;
    }

    

        


}
