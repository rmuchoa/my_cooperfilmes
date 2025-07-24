CREATE TABLE approval_user (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(50),
    user_position VARCHAR(50)
);

CREATE TABLE movie_script (
    id SERIAL PRIMARY KEY,
    text TEXT,
    status VARCHAR(50),
    client_name VARCHAR(100),
    client_phone VARCHAR(25),
    client_email VARCHAR(100) UNIQUE,
    user_id INTEGER NULL,
    CONSTRAINT fk_movie_script_approval_user
        FOREIGN KEY (user_id)
        REFERENCES approval_user(id)
        ON DELETE CASCADE
);

INSERT INTO approval_user(name, email, password, user_position) VALUES
('Bob', 'bob@email.com', '12345', 'ANALYST'),
('Marry', 'marry@email.com', '12345', 'REVIEWER'),
('Peter', 'peter@email.com', '12345', 'APPROVER'),
('Hyana', 'hyana@email.com', '12345', 'APPROVER'),
('Ted', 'ted@email.com', '12345', 'APPROVER');