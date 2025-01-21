package com.cleartax.training_superheroes.repos;

import com.cleartax.training_superheroes.dto.Superhero;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.List;

public interface SuperheroRepository extends MongoRepository<Superhero, String> {
    void deleteByName(String name);
    boolean existsByName(String name);

    Optional<Superhero> findByName(String name);
//    List<Superhero> findByName(String name);


}
