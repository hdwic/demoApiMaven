package com.domain.models.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

//import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table
@JsonIdentityInfo(    // to avoid json infinite loop
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class Supplier implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String suppName;

    @Column(length = 200, nullable = false)
    private String suppAddress;

    @Column(length = 100, nullable = true, unique = true)
    private String suppEmail;

    @ManyToMany(mappedBy = "suppliers")     //define relationship Many to Many
                                            //mapped by reference to suppliers define in product class
    //@JsonBackReference //for limiting json output (avoid infinite loop) but can be implemented using JsonIdentityInfo anotated in table above
    private Set<product> products;          
    
    



    //setter & getter
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuppName() {
        return suppName;
    }

    public void setSuppName(String suppName) {
        this.suppName = suppName;
    }

    public String getSuppAddress() {
        return suppAddress;
    }

    public void setSuppAddress(String suppAddress) {
        this.suppAddress = suppAddress;
    }

    public String getSuppEmail() {
        return suppEmail;
    }

    public void setSuppEmail(String suppEmail) {
        this.suppEmail = suppEmail;
    }

    public Set<product> getProducts() {
        return products;
    }

    public void setProducts(Set<product> products) {
        this.products = products;
    }

    


}
