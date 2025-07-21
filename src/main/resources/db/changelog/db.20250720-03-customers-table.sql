CREATE TABLE customers
(
    id                UUID         NOT NULL DEFAULT UUID_GENERATE_V4(),
    customer_group_id UUID         NOT NULL,
    created_at        TIMESTAMP    NOT NULL,
    updated_at        TIMESTAMP    NOT NULL,
    name              VARCHAR(256) NOT NULL,
    code              VARCHAR(64)  NOT NULL UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (customer_group_id) REFERENCES customer_groups (id)
);