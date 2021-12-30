package com.domain.controllers;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import com.domain.dto.ResponseData;
import com.domain.dto.SearchData;
import com.domain.models.entities.Supplier;
import com.domain.models.entities.product;
import com.domain.services.ProductServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductServices productService;

    @PostMapping
    //@RequestBody to parse data from body in POST request
    //@Valid to execute validation as defined in the product table
    public ResponseEntity<ResponseData<product>> create(@Valid @RequestBody product product, Errors errors){ 
        
        ResponseData<product> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);

        }

        responseData.setStatus(true);
        responseData.setPayload(productService.save(product));

        return ResponseEntity.ok(responseData);
    }
/*
======= this below code for throwing exception, when validation failed ======== 
=======         and revised with encapsulation as above code         ========  
public product create(@Valid @RequestBody product product, Errors errors){ 
        
        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
            throw new RuntimeException("Validation Error");
        }
================================================================================
*/




    

    @GetMapping
    public Iterable <product> findAll(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public product findOne(@PathVariable("id") Long id){
        return productService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public void removeOne (@PathVariable("id") Long id){
        productService.removeOne(id);
    }

    @PutMapping 
    public ResponseEntity<ResponseData<product>> update (@Valid @RequestBody product product, Errors errors){ //@RequestBody to parse data from body in POST request
        ResponseData<product> responseData = new ResponseData<>();

        if(errors.hasErrors()){
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
       
        }  
        return ResponseEntity.ok(responseData);  
    }

/* 
This below code already update as above
public product update(@RequestBody product product){ //@RequestBody to parse data from body in POST request
        return productService.save(product);
    }

*/
    @PostMapping("/{id")
    public void addSupplier(@RequestBody Supplier supplier, @PathVariable("id") Long productId){
        productService.addSupplier(supplier, productId);


    }    

    @PostMapping("/search/name")
    public product getProductByName(@RequestBody SearchData searchData){
        return productService.findByProductName(searchData.getSearchKey());
    }

    @PostMapping("/search/namelike")
    public List <product> getProductByNameLike(@RequestBody SearchData searchData){
        return productService.findProductByNameLike(searchData.getSearchKey());
    }

    @GetMapping("search/category/{categoryId}")
    public List <product> getProductByCategory(@PathVariable("categoryId") Long categoryId){
        return productService.findProductByCategory(categoryId);
    }

    @GetMapping("search/supplier/{supplierId}")
    public List <product> getProductBySupplier(@PathVariable("supplierId") Long supplierId){
        return productService.findProductBySupplier(supplierId);
    }


    @PostMapping("/search/{size}/{page}")
    public Iterable<product> findByNameContains
        (
            @RequestBody SearchData searchData,
            @PathVariable("size") int size,
            @PathVariable("page") int page
        )
        {
            Pageable pageable = PageRequest.of(page, size);
            return productService.findByName(searchData.getSearchKey(), pageable);
        }

    // POST methode to search product name dan sort the result using PagingAndSortingRepository
        @PostMapping("/search/{size}/{page}/{sort}")
        public Iterable<product> findByNameContains
            (
                @RequestBody SearchData searchData,
                @PathVariable("size") int size,
                @PathVariable("page") int page,
                @PathVariable("sort") String sort
            )
            {
                Pageable pageable = PageRequest.of(page, size, Sort.by("id")); //default sort in ascending order
                if(sort.equalsIgnoreCase("desc")){
                    pageable = PageRequest.of(page, size, Sort.by("id").descending()); 
                }
                return productService.findByName(searchData.getSearchKey(), pageable);
            }
        
        @PostMapping("/batch")
        public ResponseEntity<ResponseData<Iterable<product>>> createBatch (@RequestBody product[] products ){
            ResponseData<Iterable<product>> responseData = new ResponseData<>();
            responseData.setPayload(productService.saveBatch(Arrays.asList(products)));
            responseData.setStatus(true);
            return ResponseEntity.ok(responseData);
        }



}
