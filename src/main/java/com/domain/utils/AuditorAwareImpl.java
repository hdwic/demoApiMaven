package com.domain.utils;

import java.util.Optional;

import com.domain.models.entities.AppUser;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;


//this class purpose is to let JPA knows which user is logged in and save to the db which user is createdBy & updatedBy
public class AuditorAwareImpl implements AuditorAware {

    @Override
    public Optional getCurrentAuditor() {
        // TODO Auto-generated method stub

        //to get current user logged-in
        AppUser currentUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(currentUser.getEmail());
    }
    
}
