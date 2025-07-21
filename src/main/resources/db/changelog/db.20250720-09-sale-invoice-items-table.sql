CREATE TABLE sale_invoice_items
(
    id                                UUID           NOT NULL DEFAULT UUID_GENERATE_V4(),
    sale_invoice_id                   UUID           NOT NULL,
    product_id                        UUID           NOT NULL,
    unit_price                        DECIMAL(10, 2) NOT NULL CHECK (unit_price >= 0.00),
    quantity                          INTEGER        NOT NULL CHECK (quantity > 0),
    product_group_discount_percentage DECIMAL(5, 2)  NOT NULL DEFAULT 0.00 CHECK (product_group_discount_percentage >=
                                                                                  0.00 AND
                                                                                  product_group_discount_percentage <=
                                                                                  100.00),
    total_discount_percentage         DECIMAL(5, 2)  NOT NULL DEFAULT 0.00 CHECK (total_discount_percentage >=
                                                                                  0.00 AND
                                                                                  total_discount_percentage <=
                                                                                  100.00),
    total_discount_amount             DECIMAL(10, 2) NOT NULL CHECK (total_discount_amount >= 0.00),
    final_amount                      DECIMAL(10, 2) NOT NULL CHECK (final_amount >= 0.00),
    PRIMARY KEY (id),
    FOREIGN KEY (sale_invoice_id) REFERENCES sale_invoices (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);