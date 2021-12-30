package com.domain.services;

import javax.transaction.Transactional;

import com.domain.models.entities.AppUser;
import com.domain.models.repos.AppUserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepo appUserRepo;

    // @Autowired
    // private AppUser appUser;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        
        return appUserRepo.findByEmail(email)
                .orElseThrow( () -> 
                new UsernameNotFoundException(
                    String.format("user with email '%s' not found", email)));
        
    }


    public AppUser registerAppUser(AppUser user){
        
        //before registering cek user if exist in the db
        boolean userExist = appUserRepo.findByEmail(user.getEmail()).isPresent();
        if(userExist){  // if exist throw exception
            throw new RuntimeException(
                String.format("user with email '%s' already exist", user.getEmail())
            );
        }

        //before save to db, password must be encrypted
        //for this app, encryption is created with a bean in package : utils
        //anotate @Autowired in the password encoder above

       String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return appUserRepo.save(user);
    }
        



}
