package org.example.service;

import org.example.entities.RefershToken;
import org.example.entities.UserInfo;
import org.example.repository.RefershTokenRepo;
import org.example.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefershTokenService
{
   @Autowired RefershTokenRepo refershTokenRepo;

   @Autowired UserRepo userRepo;

   public  RefershToken createRefreshToken(String username ){
      UserInfo UserInfoExtract = userRepo.findByUsername(username);
      RefershToken refershToken = RefershToken.builder()
              .userInfo(UserInfoExtract)
              .token(UUID.randomUUID().toString())
              .expiryDate(Instant.now().plusMillis(600000))
              .build();
      return refershTokenRepo.save(refershToken); // by default from jpa

   }
     public RefershToken varifyExpiration (RefershToken token){
      if (token.getExpiryDate().compareTo(Instant.now())<0){
         refershTokenRepo.delete(token);
         throw new RuntimeException(token.getToken() + "refreshToken is expired .please make a new login ");

      }
      return token;

     }
   public Optional<RefershToken> findByToken (String token){
      return refershTokenRepo.findByToken(token);

   }



}
