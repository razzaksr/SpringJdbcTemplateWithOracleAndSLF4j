package com.example.springoraclejdbctemplateslf4j;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HaiRepository {
    String insertOne(Hai hai);
    String updateOne(Hai hai);
    String deleteOne(int id);
    Optional<Hai> listOne(int id);
    List<Hai> listAll();
}
