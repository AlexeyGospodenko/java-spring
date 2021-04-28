DROP TABLE IF EXISTS products;

CREATE TABLE PRODUCTS
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(255) UNIQUE NOT NULL,
    description TEXT,
    price       BIGINT
);
