package com.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class SupplierDTO {
    
    @NotEmpty(message ="name can not be empty")   //validation
    private String supp_name;

    @NotEmpty(message ="address can not be empty")
    private String supp_address;

    @NotEmpty (message ="email can not be empty")
    @Email(message ="invalid email")  //email validation
    private String supp_email;

    public String getSupp_name() {
        return supp_name;
    }

    public void setSupp_name(String supp_name) {
        this.supp_name = supp_name;
    }

    public String getSupp_address() {
        return supp_address;
    }

    public void setSupp_address(String supp_address) {
        this.supp_address = supp_address;
    }

    public String getSupp_email() {
        return supp_email;
    }

    public void setSupp_email(String supp_email) {
        this.supp_email = supp_email;
    }

    

}
