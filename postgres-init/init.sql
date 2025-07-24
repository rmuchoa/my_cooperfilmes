CREATE TABLE approval_user (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    user_position VARCHAR(50) NOT NULL
);

CREATE TABLE movie_script (
    id SERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    analysis_justification TEXT NULL,
    status VARCHAR(50) NOT NULL,
    client_name VARCHAR(100) NOT NULL,
    client_phone VARCHAR(25) NOT NULL,
    client_email VARCHAR(100) NOT NULL,
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