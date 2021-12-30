package com.domain.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.domain.models.entities.Supplier;
import com.domain.models.repos.SupplierRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Transactional
public class SupplierService {

    @Autowired
    private SupplierRepo supplierRepo ;


    public Supplier save(Supplier supplier){
        return supplierRepo.save(supplier);
    }


    public Supplier findOne(Long id){
        Optional <Supplier> supplier = supplierRepo.findById(id);
        if(!supplier.isPresent()){
            return null;
        }
        return supplier.get();
    }

    public Iterable<Supplier> findAll(){
        return supplierRepo.findAll();
    }

    public void removeOne(Long id){
        supplierRepo.deleteById(id);
    }

    public Supplier findByEmail (String email){
        return supplierRepo.findBySuppEmail(email);
    }

    public List <Supplier> findBySuppNameContains (String name){
        return supplierRepo.findBySuppNameContains(name);
    }

    public List <Supplier> findBySuppNameStartingWith (String name){
        return supplierRepo.findBySuppNameStartingWith(name);

    }

    public List <Supplier> findBySuppNameContainsOrSuppEmailContains (String name, String email){
        return supplierRepo.findBySuppNameContainsOrSuppEmailContains(name, email);
    }


}
