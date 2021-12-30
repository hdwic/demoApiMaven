package com.domain.dto;

import javax.validation.constraints.NotEmpty;

public class CategoryDTO {

    private Long id;
    
    private String categoryName;

    @NotEmpty(message = "category name can not be empty")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    

}
