package org.example.service;

import org.example.entities.UserInfo;
import org.example.entities.UserRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails extends UserInfo implements UserDetails
{
      private String username ;

      private String password;

    public Collection<? extends GrantedAuthority> authorities;

    // constructor
    ///  this cons called
    public CustomUserDetails(UserInfo byUsername){
        this.username=byUsername.getUsername();
        this.password= byUsername.getPassword();
        List<GrantedAuthority> auth =new ArrayList<>();

        for (UserRoles role :byUsername.getRoles()){
            auth.add(new SimpleGrantedAuthority(role.getName().toUpperCase()));

        }
        this.authorities=auth;

    }



    ///  just override all methods from UserDetails interface
    /// //we will not use these we just make constructor of these
       @Override
       public  Collection <? extends GrantedAuthority> getAuthorities() {
           return authorities;
       }
       @Override
       public String getPassword(){
           return password;

       }
       @Override
       public  String getUsername(){
           return username;

       }
       @Override
    public  boolean isAccountNonExpired(){
           return true ;

       }
       @Override
    public boolean isAccountNonLocked(){
           return true;
       }
       @Override
    public boolean isCredentialsNonExpired(){
           return true;

       }
       @Override
    public boolean isEnabled(){
           return true;

       }




}
