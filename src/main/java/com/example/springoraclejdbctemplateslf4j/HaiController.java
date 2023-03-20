package com.example.springoraclejdbctemplateslf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class HaiController {
    @Autowired
    HaiServices services;

    @PostMapping("/")
    public String addOne(@RequestBody Hai hai){
        return services.insertOne(hai);
    }

    @PutMapping("/")
    public String changeOne(@RequestBody Hai hai){
        return services.updateOne(hai);
    }

    @DeleteMapping("/{id}")
    public String removeOne(@PathVariable("id") int id){
        return services.deleteOne(id);
    }

    @GetMapping("/{id}")
    public Optional<Hai> readOne(@PathVariable("id") int id){
        return services.listOne(id);
    }

    @GetMapping("/")
    public List<Hai> readAll(){
        return services.listAll();
    }
}
