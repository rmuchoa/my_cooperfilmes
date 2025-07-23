CREATE TABLE movie_script (
    id SERIAL PRIMARY KEY,
    text TEXT,
    status VARCHAR(50),
    client_name VARCHAR(100),
    client_phone VARCHAR(25),
    client_email VARCHAR(100) UNIQUE
);

--INSERT INTO person (name, email) VALUES
--('Alice', 'alice@example.com'),
--('Bob', 'bob@example.com');