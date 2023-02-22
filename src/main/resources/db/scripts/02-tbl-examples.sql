-- liquibase formatted sql

-- changeset farmu:ID-02 examples table
CREATE TABLE IF NOT EXISTS examples
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    created             TIMESTAMP NOT NULL,
    updated             TIMESTAMP NOT NULL,
    username            VARCHAR(255) NULL,
    operator_a          NUMERIC(19,10) NULL,
    operator_b          NUMERIC(19,10) NULL,
    percentage_result   NUMERIC(19,10) NULL,
    result              NUMERIC(19,10) NULL
);

# CREATE INDEX IF NOT EXISTS IDX_examples_username ON examples(username);
