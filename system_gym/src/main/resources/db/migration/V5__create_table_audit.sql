CREATE TABLE audit
(
    id_audit    UUID                        NOT NULL,
    description VARCHAR(255)                NOT NULL,
    date        TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    class       VARCHAR(255)                NOT NULL,
    local_adress      VARCHAR(255)                NOT NULL,
    CONSTRAINT pk_audit PRIMARY KEY (id_audit)
);