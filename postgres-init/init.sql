CREATE TABLE movie_script (
    id SERIAL PRIMARY KEY,
    text TEXT,
    status VARCHAR(50),
    client_name VARCHAR(100),
    client_phone VARCHAR(25),
    client_email VARCHAR(100) UNIQUE
);

CREATE TABLE approval_user (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(50),
    user_position VARCHAR(50)
)
