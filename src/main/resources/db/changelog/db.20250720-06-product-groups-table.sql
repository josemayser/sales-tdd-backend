CREATE TABLE product_groups
(
    id                  UUID          NOT NULL DEFAULT UUID_GENERATE_V4(),
    created_at          TIMESTAMP     NOT NULL,
    updated_at          TIMESTAMP     NOT NULL,
    name                VARCHAR(128)  NOT NULL UNIQUE,
    discount_percentage DECIMAL(5, 2) NOT NULL DEFAULT 0.00 CHECK (discount_percentage >= 0.00 AND discount_percentage <= 100.00),
    PRIMARY KEY (id)
);