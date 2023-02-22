-- liquibase formatted sql

-- changeset farmu:ID-01 clients table
CREATE TABLE IF NOT EXISTS clients
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    created     TIMESTAMP NOT NULL,
    updated     TIMESTAMP NOT NULL,
    username    VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    enabled     BOOLEAN NOT NULL,
    type        VARCHAR(255) NOT NULL
);

# -- changeset farmu:ID-02 index table
# CREATE UNIQUE INDEX IF NOT EXISTS idx_clients_username ON public.clients(username);
