package com.cleartax.training_superheroes.services;

import com.cleartax.training_superheroes.dto.Superhero;
import com.cleartax.training_superheroes.dto.SuperheroRequestBody;
import com.cleartax.training_superheroes.repos.SuperheroRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SuperheroService {

    private SuperheroRepository superheroRepository;

    public SuperheroService(SuperheroRepository superheroRepository){
        this.superheroRepository = superheroRepository;
    }

//    public Superhero getSuperhero(String name, String universe){
//        if(null != name){
//            return getByName(name);
////        } else if (null != universe){
////            return getByUniverse(universe);
//        } else{
//            throw new RuntimeException("Not found");
//        }
//    }

//    private Superhero getByName(String name){
//        return getDummyDate(name);
//    }

//    private Superhero  getByUniverse(String universe){
//        Superhero superhero =  new Superhero();
//        superhero.setUniverse(universe);
//        return superhero;
//    }

    private Superhero getDummyDate(String name){
        Superhero superhero =  new Superhero();
        superhero.setName(name);
        return superhero;
    }

    public Superhero persistSuperhero(SuperheroRequestBody requestBody){
        Superhero superhero = new Superhero();
        superhero.setName(requestBody.getName());
        superhero.setPower(requestBody.getPower());
        superhero.setUniverse(requestBody.getUniverse());

        return superheroRepository.save(superhero);
    }

    public void deleteById(String id) {
        if (superheroRepository.existsById(id)) {
            superheroRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Document with ID " + id + " does not exist");
        }
    }

    public void deleteByName(String name) {
        try {
            if (superheroRepository.existsByName(name)) {
                superheroRepository.deleteByName(name);
            } else {
                throw new IllegalArgumentException("Superhero with name " + name + " does not exist");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new RuntimeException("An unexpected error occurred while deleting the superhero with name " + name, e);
        }
    }

    public List<Superhero> getAllSuperheroes() {
        return superheroRepository.findAll();
    }

    public Superhero getByNameHero(String name) {
        Optional<Superhero> superheroOptional = superheroRepository.findByName(name);
        if (superheroOptional.isPresent()) {
            return superheroOptional.get();
        } else {
            throw new IllegalArgumentException("Superhero with name " + name + " does not exist");
        }
    }


}
