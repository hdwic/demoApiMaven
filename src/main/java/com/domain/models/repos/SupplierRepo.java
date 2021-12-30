package com.domain.models.repos;


import java.util.List;

import javax.websocket.server.PathParam;

import com.domain.models.entities.Supplier;

// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {
    
    // using derived query
    //derived query won't work if entity name using '_'
   public Supplier findBySuppEmail (String suppEmail);

//    @Query ("SELECT s FROM Supplier s WHERE s.supp_email= :supp_email")
//     public Supplier findByEmail(@PathParam("supp_email") String supp_email);


    public List <Supplier> findBySuppNameContains (String name);

    public List <Supplier> findBySuppNameStartingWith (String name);

    public List <Supplier> findBySuppNameContainsOrSuppEmailContains(String name, String email);
    

}
