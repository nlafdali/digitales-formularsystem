package com.example.formsys.repository;

import com.example.formsys.model.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends JpaRepository<Form, Long> {

    // Methode, um zu prüfen, ob ein Formular mit einem bestimmten Titel existiert
    boolean existsByTitle(String title);

}
