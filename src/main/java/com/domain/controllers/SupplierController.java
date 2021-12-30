package com.domain.controllers;

import java.util.List;

import javax.validation.Valid;

import com.domain.dto.ResponseData;
import com.domain.dto.SearchData;
import com.domain.dto.SupplierDTO;
import com.domain.models.entities.Supplier;
import com.domain.services.SupplierService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    
    @Autowired
    private SupplierService supplierService;

    @Autowired    //inject bean defined in DemoApplication.java
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity <ResponseData <Supplier>> create (@Valid @RequestBody SupplierDTO supplierDTO, Errors errors){
        
        ResponseData<Supplier> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        

        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));

        return ResponseEntity.ok(responseData);
        

        /*
        =this below code will return ResponseEntity of responseData
        =and ResponseData must be manually set, because it is inside class of
        =ResponseEntity that use SupplierDTO as data transaction object
        = ResponseEntity => ResponseSTO => ResponseData

        Supplier supplier = new Supplier();
        supplier.setSupp_name(supplierDTO.getSupp_name());
        supplier.setSupp_address(supplierDTO.getSupp_address());
        supplier.setSupp_email(supplierDTO.getSupp_email());

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));

        return ResponseEntity.ok(responseData);

        */
            

    }

    @GetMapping
    public Iterable<Supplier> findAll(){
        return supplierService.findAll();

    }

    @GetMapping("/{id}")
    public Supplier findOne(@PathVariable("id") Long id){
        return supplierService.findOne(id);
    }

    @PutMapping   //for update request, will be employed using the same code as create request
    public ResponseEntity <ResponseData <Supplier>> update (@Valid @RequestBody SupplierDTO supplierDTO, Errors errors){
        
        ResponseData<Supplier> responseData = new ResponseData<>();
        if(errors.hasErrors()){
            for (ObjectError error: errors.getAllErrors()) {
                responseData.getMessages().add(error.getDefaultMessage());
            }
            responseData.setStatus(false);
            responseData.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        

        Supplier supplier = modelMapper.map(supplierDTO, Supplier.class);

        responseData.setStatus(true);
        responseData.setPayload(supplierService.save(supplier));

        return ResponseEntity.ok(responseData);
     
    }
    @PostMapping("/search/email")
    public Supplier findByEmail(@RequestBody SearchData searchData){
        return supplierService.findByEmail(searchData.getSearchKey());
    }

    @PostMapping("/search/namecontain")
    public List<Supplier> findBySuppNameContains(@RequestBody SearchData searchData){
        return supplierService.findBySuppNameContains(searchData.getSearchKey());
    }

    @PostMapping("/search/namestartwith")
    public List<Supplier> findBySuppNameStartingWith(@RequestBody SearchData searchData){
        return supplierService.findBySuppNameStartingWith(searchData.getSearchKey());
    }

    @PostMapping("/search/nameemailcontain")
    public List<Supplier> findBySuppNameContainsOrSuppEmailContains(@RequestBody SearchData searchData){
        return supplierService.findBySuppNameContainsOrSuppEmailContains(searchData.getSearchKey(), searchData.getSearchKey_2());
    }

}
