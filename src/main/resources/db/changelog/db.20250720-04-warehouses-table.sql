CREATE TABLE warehouses
(
    id         UUID         NOT NULL DEFAULT UUID_GENERATE_V4(),
    created_at TIMESTAMP    NOT NULL,
    updated_at TIMESTAMP    NOT NULL,
    name       VARCHAR(128) NOT NULL UNIQUE,
    location   TEXT,
    PRIMARY KEY (id)
);