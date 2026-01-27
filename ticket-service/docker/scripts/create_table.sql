CREATE TABLE tickets(
    id bigint NOT NULL,
    excursion_id bigint NOT NULL,
    price integer NOT NULL,
    date_start DATE,
    date_end DATE,
    booking VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
)