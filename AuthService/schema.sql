CREATE DATABASE auth;

CREATE TABLE users (
    id UUID PRIMARY KEY,
    username TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    role TEXT NOT NULL CHECK ( role IN ( 'ADMIN', 'USER' ) )
)