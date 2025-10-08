package com.example.formsys.service;

import com.example.formsys.model.Form;

import java.util.List;
import java.util.Optional;

public interface FormService {

    List<Form> findAll();
    Optional<Form> findById(Long id);
    Form create(Form form);
    Optional<Form> update(Long id, Form updateForm);
    boolean delete(Long id);
}
