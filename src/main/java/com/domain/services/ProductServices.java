package com.domain.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import com.domain.models.entities.Supplier;
import com.domain.models.entities.product;
import com.domain.models.repos.ProductRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class ProductServices {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private SupplierService supplierService;  //use supplier service in findProductBySupplier

    public product save (product product){
        return productRepo.save(product);
    }

    public product findOne(Long id){
        return productRepo.findById(id).get();

    }

    public Iterable<product> findAll(){
        return productRepo.findAll();
    } 

    public void removeOne(Long id){
        productRepo.deleteById(id);
    }

    // public List <product> findByName( String name){
    //     return productRepo.findByNameContains(name);  // to return method defined in 'ProductRepo' not available in CrudRepositroy
    
    // } 

    public void addSupplier(Supplier supplier, Long productId){
        product product = findOne(productId);

        if(product == null){
            throw new RuntimeException("Product with id = "+productId +" not found");
        }

        save(product);


    }


    public product findByProductName(String name){
        return productRepo.findProductByName(name);
    }

    public List <product> findProductByNameLike(String name){
        return productRepo.findProductByNameLike("%"+name+"%"); //just like SQL query where "%" used with query LIKE
    }


    public List <product> findProductByCategory( Long categoryId ){
        return productRepo.findProductByCategory(categoryId);
    }


    public List<product> findProductBySupplier( Long supplierId){ //find Product but only using Id
        
        Supplier supplier = supplierService.findOne(supplierId);  //find supplierId if exist in Supplier

        if(supplier == null){
            return new ArrayList<product>();
        }

        return productRepo.findProductBySupplier(supplier);

    }


    public Iterable<product> findByName (String name, Pageable pageable){
        return productRepo.findByNameContains(name, pageable);
    }


    //save batch of product to db
    public Iterable <product> saveBatch(Iterable <product> product){
        return productRepo.saveAll(product);
    }



}
