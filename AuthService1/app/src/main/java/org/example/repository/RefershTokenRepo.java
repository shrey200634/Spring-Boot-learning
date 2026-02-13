package org.example.repository;

import org.example.entities.RefershToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RefershTokenRepo extends CrudRepository<RefershToken,Integer> {
    // JpA
    Optional <RefershToken> findByToken(String token_1);


}
