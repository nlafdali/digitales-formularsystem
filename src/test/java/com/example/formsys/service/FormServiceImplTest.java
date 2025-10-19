package com.example.formsys.service;

import com.example.formsys.model.Form;
import com.example.formsys.repository.FormRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mockito;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

public class FormServiceImplTest {

    FormRepository formRepository;
    FormServiceImpl formService;

    @BeforeEach
    void setUp(){
        formRepository= Mockito.mock(FormRepository.class);
        formService = new FormServiceImpl(formRepository);
    }

    @Test
    void create_shouldThrowWhenTitleExists(){
        Form f = new Form();
        String titel_test= "Duplicate";
        f.setTitle(titel_test);
        when(formRepository.existsByTitle(titel_test)).thenReturn(true);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> formService.create(f));

        assertTrue(ex.getMessage().toLowerCase().contains("bereits"));
        verify(formRepository, never()).save(any());
    }

    @Test
    void create_shouldSaveWhenTitleIsNew(){
        Form f = new Form();
        f.setTitle("unique");
        f.setDescription("desc");

        when(formRepository.existsByTitle("unique")).thenReturn(false);
        Form savedForm = new Form();
        savedForm.setId(1L);
        savedForm.setTitle("Unique");
        savedForm.setDescription("desc");

        when(formRepository.save(any(Form.class))).thenReturn(savedForm);

        Form saved  = formService.create(f);

        assertNotNull(saved);
        assertEquals(1L, saved.getId() );
        verify(formRepository).save(any(Form.class));

    }

    @Test
    void update_shouldReturnEmptyWhenNotFound(){
        when(formRepository.findById(99L)).thenReturn(Optional.empty() );

        Optional<Form> result = formService.update(99L, new Form());
        assertTrue(result.isEmpty());
    }

    @Test
    void update_shouldModifyWhenFound(){
        Form existing = new Form();
        existing.setId(1L);
        existing.setTitle("Old");
        existing.setDescription("new desc");

        when(formRepository.findById(1L)).thenReturn(Optional.of(existing));

        Answer<Form> answer = new Answer<Form>(){

            @Override
            public Form answer(InvocationOnMock invocation){
                //Das Argument holen, das an save() übergeben wurde
                Object[] args = invocation.getArguments();
                Form savedForm = (Form) args[0];
                return savedForm;
            }

        };
        //neues Formular mit neuen Werten aktualisieren

        Form update = new Form();
        update.setTitle("New");
        update.setDescription("new desc");

        //Methode im Service aufrufen
        Optional<Form> result = formService.update(1L, update);

        //Überprüfen, ob ein Ergebnis vorhanden ist
        assertTrue(result.isPresent());

        //Prüfen, ob die Felder korrekt aktualisert wurden
        assertEquals("new", result.get().getTitle());
        assertEquals("new desc", result.get().getDescription());

        // Sicherstellen, dass save() tatsächlich aufgerufen wurde
        verify(formRepository).save(existing);
    }




}
