package com.domain.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.domain.models.entities.Category;
import com.domain.models.repos.CategoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryService {
    
    @Autowired
    private CategoryRepo categoryRepo;

    public Category save (Category category){
        if(category.getId() != null){
            Category currentCategory = categoryRepo.findById(category.getId()).get();
            currentCategory.setCategoryName(category.getCategoryName()); // if id is not null, overwrite category name only
            category = currentCategory;
        
        }
        return categoryRepo.save(category);
    }

    public Category findOne(Long id){
        Optional <Category> category = categoryRepo.findById(id);
        if(!category.isPresent()){
            return null;
        }
        return category.get();
    }


    public Iterable <Category> findAll(){
        return categoryRepo.findAll();
    }

    public void removeOne(Long id){
        categoryRepo.deleteById(id);
    } 

    public Iterable<Category> findByCategoryNameContains (String categoryName, Pageable pageable){
        return categoryRepo.findByCategoryNameContains(categoryName, pageable);
    }

}
