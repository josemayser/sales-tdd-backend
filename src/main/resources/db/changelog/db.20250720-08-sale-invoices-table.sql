CREATE TABLE sale_invoices
(
    id                                 UUID           NOT NULL DEFAULT UUID_GENERATE_V4(),
    customer_id                        UUID           NOT NULL,
    warehouse_id                       UUID           NOT NULL,
    payment_condition_id               UUID           NOT NULL,
    created_at                         TIMESTAMP      NOT NULL,
    customer_group_discount_percentage DECIMAL(5, 2)  NOT NULL DEFAULT 0.00 CHECK (customer_group_discount_percentage >=
                                                                                   0.00 AND
                                                                                   customer_group_discount_percentage <=
                                                                                   100.00),
    tax_name                           VARCHAR(256)   NOT NULL,
    tax_id                             VARCHAR(64)    NOT NULL,
    total_amount                       DECIMAL(10, 2) NOT NULL CHECK (total_amount >= 0.00),
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customers (id),
    FOREIGN KEY (warehouse_id) REFERENCES warehouses (id),
    FOREIGN KEY (payment_condition_id) REFERENCES payment_conditions (id)
);