package com.cleartax.training_superheroes.controllers;

import com.cleartax.training_superheroes.dto.Superhero;
import com.cleartax.training_superheroes.dto.SuperheroRequestBody;
import com.cleartax.training_superheroes.services.SuperheroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SuperheroController {

    private final SuperheroService superheroService;

    @Autowired
    public SuperheroController(SuperheroService superheroService) {
        this.superheroService = superheroService;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "username", defaultValue = "World") String username) {
        return String.format("Hello %s!", username);
    }
//
//    @GetMapping("/superhero")
//    public Superhero getSuperhero(@RequestParam(value = "name", defaultValue = "Batman") String name,
//                                  @RequestParam(value = "universe", defaultValue = "DC") String universe) {
//        return superheroService.getSuperhero(name, universe);
//    }

    @PostMapping("/superhero")
    public Superhero persistSuperhero(@RequestBody SuperheroRequestBody superhero) {
        return superheroService.persistSuperhero(superhero);
    }

    @DeleteMapping("superhero/{id}")
    public String  deleteById(@PathVariable String id) {
        try {
            superheroService.deleteById(id);
            return "Deleted document with ID: " + id;
        } catch (IllegalArgumentException e) {
            return "There is some error";

        }
    }

    @DeleteMapping("/superhero")
    public ResponseEntity<String> deleteByName(@RequestParam String name) {
        try {
            superheroService.deleteByName(name);
            return ResponseEntity.ok("Deleted superhero with name: " + name);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/superheroes")
    public ResponseEntity<List<Superhero>> getAllSuperheroes() {
        List<Superhero> superheroes = superheroService.getAllSuperheroes();
        return ResponseEntity.ok(superheroes);
    }

    @GetMapping("/superhero")
    public ResponseEntity<?> getSuperhero(@RequestParam String name) {
        try {
            Superhero superhero = superheroService.getByNameHero(name);
            return ResponseEntity.ok(superhero);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/superhero")
    public void updateSuperhero(){

    }


}