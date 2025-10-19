
create TABLE IF NOT EXISTS forms(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(2000),
    schema_json CLOB,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
    );

INSERT INTO forms (title, description, schema_json, created_at, updated_at)
VALUES ('Urlaubsantrag', 'Formular für Urlaubsanträge', '{}', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());

INSERT INTO forms(title, description, schema_json, created_at, updated_at)
VALUES ('Feedback', 'Kundenfeedback', '{}', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP());