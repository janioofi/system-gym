CREATE TABLE tb_user (
    id_user TEXT PRIMARY KEY UNIQUE NOT NULL ,
    login TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role TEXT NOT NULL
);