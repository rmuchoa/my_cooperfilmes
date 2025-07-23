CREATE TABLE movie_script (
    id SERIAL PRIMARY KEY,
    text TEXT,
    client_name VARCHAR(100),
    client_phone VARCHAR(25),
    client_email VARCHAR(100) UNIQUE
);
