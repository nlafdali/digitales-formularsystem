package com.example.formsys.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name= "forms")
public class Form {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(length =2000)
    private String description;
    @Lob
    private String schemaJson;
    private LocalDateTime creatAt;

    public Form(){
        this.creatAt = LocalDateTime.now();
    }
    // <--Getter und Setter (manuell, kein Lombok) -->

    public Long getId(){ return id;}
    public void setId(Long id){this.id = id;}

    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}

    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}

    public String getSchemaJson(){return schemaJson;}
    public void setSchemaJson(String schemaJson){this.schemaJson = schemaJson;}
    public LocalDateTime getCreatAt(){return creatAt;}
    public void setCreatAt(LocalDateTime creatAt){this.creatAt = creatAt;}

}
