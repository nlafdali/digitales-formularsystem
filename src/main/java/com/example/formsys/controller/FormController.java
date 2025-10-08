package com.example.formsys.controller;

import com.example.formsys.model.Form;
import com.example.formsys.repository.FormRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/forms")
public class FormController {

    private final FormRepository formRepository;

    public FormController(FormRepository formRepository)
    {
        this.formRepository = formRepository;
    }
    @PostMapping
    public ResponseEntity<Form> create(@RequestBody Form form)
    {
        // Eingabe prüfen (Validierung). prüfen, ob das Feld 'title' leer ist.
        // Wenn kein Titel angegeben wurde, wird eine Fehlermeldung zurückgesendet.
     if(form.getTitle() == null || form.getTitle().isBlank()){
         // HTTP 400 = Bad Request (ungültige Anfrage)
        return ResponseEntity.badRequest().build();};
        // Das Repository kümmert sich um das Speichern (INSERT-Befehl in der H2-Datenbank).
     Form saved = formRepository.save(form);
        // HTTP-Status 201 Created (Ressource erfolgreich erstellt)
        // Im Body das gespeicherte Formular als JSON (inkl. automatisch vergebener ID)
     return ResponseEntity.status(HttpStatus.CREATED).body(saved);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Form> get(@PathVariable Long id) {

        // Formular mit der gegebenen ID aus der Datenbank holen
        Optional<Form> optionalForm = formRepository.findById(id);

        // Prüfen, ob ein Formular gefunden wurde
        if (optionalForm.isPresent()) {
            //  Wenn vorhanden → HTTP 200 OK + Formular im Body zurückgeben
            Form form = optionalForm.get();
            return ResponseEntity.ok(form);
        } else {
            // Wenn NICHT vorhanden → HTTP 404 Not Found zurückgeben
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Form>> getAllForms(){
        //Alle Datensätze aus der Datenbank laden
        List<Form> forms = formRepository.findAll();
        System.out.println(" Es wurden " + forms.size() + " Formulare gefunden");

        //wenn keine Formulare existieren -> leere Liste zurückgeben (Status 200)
        return ResponseEntity.ok(forms);

    }
    @PutMapping("/{id}")

    public ResponseEntity<Form> updateForm(@PathVariable Long id, @RequestBody Form updateForm){
        // prüfen, ob das Formular exsistiert
        Optional<Form> existing = formRepository.findById(id);

        if(existing.isEmpty()){
            // wenn nicht gefunden -> 404 zurückgeben
            return ResponseEntity.notFound().build();
        }
        // das alte Formular holen
        Form form = existing.get();

        //Felder überschreiben
        form.setTitle(updateForm.getTitle());
        form.setDescription(updateForm.getDescription());
        form.setSchemaJson(updateForm.getSchemaJson());

        //Speichern
        Form saved = formRepository.save(form);
        // Antwort mit aktualisiertem Objekt zurückgeben
        return ResponseEntity.ok(saved);
    }
    /*public ResponseEntity<Form> updateForm(@PathVariable Long id, @RequestBody Form updateForm) {
        Optional<Form> existing = formRepository.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Debug: was kommt im Request wirklich an?
        System.out.println("DEBUG: updateForm.title  = '" + updateForm.getTitle() + "'");
        System.out.println("DEBUG: updateForm.description = '" + updateForm.getDescription() + "'");

        Form form = existing.get();
        System.out.println("DEBUG: before - form.title = '" + form.getTitle() + "'");

        // Nur überschreiben, wenn der neue Wert nicht null/leer ist
        if (updateForm.getTitle() != null && !updateForm.getTitle().isBlank()) {
            form.setTitle(updateForm.getTitle());
        }
        if (updateForm.getDescription() != null && !updateForm.getDescription().isBlank()) {
            form.setDescription(updateForm.getDescription());
        }
        if (updateForm.getSchemaJson() != null) {
            form.setSchemaJson(updateForm.getSchemaJson());
        }

        Form saved = formRepository.save(form);
        System.out.println("DEBUG: after - saved.title = '" + saved.getTitle() + "'");

        return ResponseEntity.ok(saved);
    }*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteForm(@PathVariable Long id){
        // prüfen, ob der Datensatz exestiert
        if(!formRepository.existsById(id)){
            // Falls nicht gefunden -> 404 Not Found
            return ResponseEntity.notFound().build();
        }

        // Löschen
        formRepository.deleteById(id);
        // kein Body zurückgeben, nur Status 204 No Content
        return ResponseEntity.noContent().build();
    }
}
