CREATE TABLE users
(
    id bigint NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL,
    UNIQUE (name, email),
    PRIMARY KEY (id)
);

CREATE TABLE orders
(
    id bigint NOT NULL,
    order_status VARCHAR(40) NOT NULL,
    user_id BIGINT,
    excursion_id BIGINT,
    PRIMARY KEY (id),
    CONSTRAINT fk_users FOREIGN KEY (user_id)
        REFERENCES users (id)
)
