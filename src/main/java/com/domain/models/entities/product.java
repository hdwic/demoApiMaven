package com.domain.models.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name ="tbl_product")
@JsonIdentityInfo(   // to avoid json infinite loop
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class product implements Serializable{  //filename and name of this class should start w/ uppercase
    

    //below code will create table with field : Id, name, description, price
    @Id  //anotation for setting ID in table "tbl_product"
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //setting id to be primary key
    private long id;
    

    @Column(name="product_name", length = 100)   //this will change the field 'name' to be 'product_name' and set 'length'
    @NotEmpty(message="name can not be empty")  //anotation to include validation, make sure update dependency Validation & Starter in pom.xml
    private String name;
    
    @Column(name="product_desc", length=300) //this will change the field 'description' to be 'product_desc' and set 'length'
    @NotEmpty(message="description can not be empty")
    
    private String description;
    
    private double price;

    @ManyToOne      //define relationship w/ tbl_category as Many to One
    private Category category;

    @ManyToMany     //define relationship Many to Many w/ tbl_supplier
    //@JsonManagedReference  //for limiting json output (avoid infinite loop) but can be implemented using JsonIdentityInfo anotated in table above
    @JoinTable (    //create table to handle Many to Many relationship
        name = "tbl_product_supplier",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "supplier_id")
        )
    private Set<Supplier> suppliers;  // use this to add annotation with related table


    //==== setter & getter ======

    //below to create constructor automatically, right click=> source action => contructor 
    //in command pallette create contructor for 'empty' class 
    public product() {
    }

    //below to create constructor automatically, right click=> source action => contructor 
    //in command pallette select to create contructor for all 'tbl_product' field
    public product(long id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    //below to create setter getter automatically, right click=> source action => getter setter 
    //in command pallette select to create getter setter for all 'tbl_product' field
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(Set<Supplier> suppliers) {
        this.suppliers = suppliers;
    }

    
    
}
