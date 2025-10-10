package com.example.formsys.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FormDto {
    @NotBlank(message = "Titel darf  nicht leer sein")
    @Size(max = 255, message = "Titel darf maximal 255 Zwichen haben")
    private String title;
    @Size(max = 2000, message = "Besjreibung zu lang" )
    private String description;

    private String schemaJson;

    public FormDto(){}
    //Getter und Setter

    public String getTitle()
    { return title;   }

    public void setTitle(String title)
    {this.title = title;}

    public String getDescription()
    {return description;}

    public void setDescription(String description)
    {this.description = description;}

    public String getSchemaJson()
    {return schemaJson;}

    public void setSchemaJson(String schemaJson)
    {this.schemaJson = schemaJson;}
}
