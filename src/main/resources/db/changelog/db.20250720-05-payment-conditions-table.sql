CREATE TABLE payment_conditions
(
    id         UUID        NOT NULL DEFAULT UUID_GENERATE_V4(),
    created_at TIMESTAMP   NOT NULL,
    updated_at TIMESTAMP   NOT NULL,
    name       VARCHAR(64) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);