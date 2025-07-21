CREATE TABLE products
(
    id               UUID           NOT NULL DEFAULT UUID_GENERATE_V4(),
    product_group_id UUID           NOT NULL,
    created_at       TIMESTAMP      NOT NULL,
    updated_at       TIMESTAMP      NOT NULL,
    name             VARCHAR(256)   NOT NULL,
    code             VARCHAR(64)    NOT NULL UNIQUE,
    price            DECIMAL(10, 2) NOT NULL CHECK (price >= 0.00),
    PRIMARY KEY (id),
    FOREIGN KEY (product_group_id) REFERENCES product_groups (id)
);