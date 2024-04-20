CREATE TABLE product
(
    id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name        VARCHAR(255)                            NOT NULL,
    price       DECIMAL(20, 2),
    quantity    INTEGER                                 NOT NULL,
    description VARCHAR(255),
    CONSTRAINT pk_product PRIMARY KEY (id)
);