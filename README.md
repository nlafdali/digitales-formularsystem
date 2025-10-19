# digitales-formularsystem 
# Formularverwaltungssystem

Ein CRUD-basiertes *Spring Boot*-Projekt zur Verwaltung von Formularen.  
FormSys bietet eine einfache REST-API, mit der Formulare erstellt, abgerufen, aktualisiert und gelöscht werden können.  
Das Projekt nutzt *Spring Boot*, *Spring Data JPA*, *H2-Datenbank* und *JUnit 5 / Mockito* für Tests.

---

## Features

- *Formulare erstellen, lesen, aktualisieren, löschen (CRUD)*
- *REST API* unter `/api/forms`
- *Validierung* mit `@Valid` und DTOs
- *Persistenz* mit JPA und H2 In-Memory-Datenbank
- *Flyway-Migrationen* für DB-Versionierung
- *Unit-Tests* (Mockito) und **Integrationstests** (MockMvc)
