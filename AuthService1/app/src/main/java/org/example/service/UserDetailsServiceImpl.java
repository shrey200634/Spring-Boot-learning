package org.example.service;

import org.example.entities.UserInfo;
import org.example.model.UserInfoDto;
import org.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private  UserRepo userRepo;


    @Autowired
    private  PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        UserInfo user = userRepo.findByUsername(username);
        if(user ==null){
            throw new UsernameNotFoundException("could not found user ...!!");

        }
        return new CustomUserDetails(user);

    }

    public UserInfo checkIfUserAlreadyExixt(UserInfoDto userInfoDto) {
        return userRepo.findByUsername(userInfoDto.getUsername());
    }


    public Boolean signupUser(UserInfoDto userInfoDto){


        userInfoDto.setPassword(passwordEncoder.encode(userInfoDto.getPassword()));
        if (checkIfUserAlreadyExixt(userInfoDto) != null) {
            return false;
        }

        String userId = UUID.randomUUID().toString();
        userRepo.save(new UserInfo(userId, userInfoDto.getUsername(),
                userInfoDto.getPassword(), new HashSet<>()));

        return true ;


    }





}
