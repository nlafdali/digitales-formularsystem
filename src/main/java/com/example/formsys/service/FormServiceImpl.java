package com.example.formsys.service;

import com.example.formsys.model.Form;
import com.example.formsys.repository.FormRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FormServiceImpl implements FormService{

    private final FormRepository formRepository;

    public FormServiceImpl(FormRepository formRepository)
    {
        this.formRepository = formRepository;
    }

    @Override
    public List<Form> findAll()
    { return formRepository.findAll(); }
    @Override
    public Optional<Form> findById(Long id)
    {return formRepository.findById(id);}

    @Override
    public Form create(Form form) {
        //Titel prüfen
        if(form.getTitle() == null || form.getTitle().isBlank()){
            throw new IllegalArgumentException("Der Titel dar nicht leer sein!");
        }
        //Prüfen, ob der Titel schon existiert
        if(formRepository.existsByTitle(form.getTitle())){
            throw new IllegalArgumentException("Ein Formular mit diesem Titel existert bereits!");
        }

        // Optional: Beschreibung darf nicht null sein
        if(form.getDescription()== null){
            form.setDescription("");
        }

        return formRepository.save(form);}
    @Override
    public Optional<Form> update(Long id, Form updateForm) {
        // Datensats mit der gegebenen Id suchen
        Optional<Form> optionalForm = formRepository.findById(id);
        // prüfen, ob Formular existiert
        if(optionalForm.isEmpty()){
            return Optional.empty(); // keine einträge gefunden
        }
        // das bestehende Formular holen
        Form existing = optionalForm.get();
        //Felder aktualisieren (nur wenn neue Werte übergeben würde)
        if(updateForm.getTitle() != null && !updateForm.getTitle().isBlank()){
            existing.setTitle(updateForm.getTitle());
        }

        if (updateForm.getDescription() !=null){
            existing.setDescription(updateForm.getDescription());
        }
        if(updateForm.getSchemaJson() != null)
        { existing.setSchemaJson(updateForm.getSchemaJson());}

        existing.setUpdateAt(java.time.LocalDateTime.now());

        //in der Datenbank speichern
        Form saved = formRepository.save(existing);
        //aktualisiertes Objekt zurückgeben
        return Optional.of(saved);
    }
    @Override
    public boolean delete(Long id){
        if(!formRepository.existsById(id))
        {  return false; }
        formRepository.deleteById(id);
        return true;
    }
}
