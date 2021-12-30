package com.domain.models.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

import com.domain.models.entities.AppUser;

public interface AppUserRepo extends PagingAndSortingRepository <AppUser, Long>{
    Optional <AppUser> findByEmail (String email);


}
