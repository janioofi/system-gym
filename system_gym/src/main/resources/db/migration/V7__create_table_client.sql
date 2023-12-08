CREATE TABLE tb_client
(
    id_client       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name            VARCHAR(255)                            NOT NULL,
    cpf             VARCHAR(255)                            NOT NULL,
    birth_date      date                                    NOT NULL,
    register_date   date                                    NOT NULL,
    updated_date    TIMESTAMP WITHOUT TIME ZONE             NOT NULL,
    due_date        INTEGER,
    email           VARCHAR(255)                            NOT NULL,
    active          BOOLEAN                                 NOT NULL,
    id_receptionist BIGINT                                  NOT NULL,
    id_plan         BIGINT                                  NOT NULL,
    id_user         BIGINT                                  NOT NULL,
    CONSTRAINT pk_tb_client PRIMARY KEY (id_client)
);

ALTER TABLE tb_client
    ADD CONSTRAINT uc_tb_client_cpf UNIQUE (cpf);

ALTER TABLE tb_client
    ADD CONSTRAINT uc_tb_client_id_user UNIQUE (id_user);

ALTER TABLE tb_client
    ADD CONSTRAINT FK_TB_CLIENT_ON_ID_PLAN FOREIGN KEY (id_plan) REFERENCES tb_plan (id_plan);

ALTER TABLE tb_client
    ADD CONSTRAINT FK_TB_CLIENT_ON_ID_RECEPTIONIST FOREIGN KEY (id_receptionist) REFERENCES tb_receptionist (id_receptionist);

ALTER TABLE tb_client
    ADD CONSTRAINT FK_TB_CLIENT_ON_ID_USER FOREIGN KEY (id_user) REFERENCES tb_user (id_user);