package com.domain.models.repos;



import java.util.List;

import javax.websocket.server.PathParam;

import com.domain.models.entities.Supplier;
import com.domain.models.entities.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepo extends PagingAndSortingRepository <product, Long> {  //interface to do CRUD operation on table 'Product' and defined type of primary key 'long'
 
    //class CrudRepository already contain methode of REST API : GET, POST, UPDATE, DELETE

    //below code to make methode not available in class CrudRepository 
    //Spring will auto generate SQL query based on the name of the function : 'findByNameContains'
    // List<product> findByNameContains (String name);


    @Query("SELECT p FROM product p WHERE p.name= :name")
    public product findProductByName(@PathParam("name") String name);

    @Query("SELECT p FROM product p WHERE p.name LIKE :name")
    public List<product> findProductByNameLike(@PathParam("name") String name);

    @Query("SELECT p FROM product p WHERE p.category.id = :categoryId")
    public List <product> findProductByCategory(@PathParam("categoryId") Long categoryId);

    @Query ("SELECT p FROM product p WHERE :supplier MEMBER OF p.suppliers")
    public List <product> findProductBySupplier(@PathParam("supplier") Supplier supplier);


    //using paging&SortingRepo
    Page <product> findByNameContains (String name, Pageable pageable);


}
