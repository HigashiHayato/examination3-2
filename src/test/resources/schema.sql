CREATE TABLE IF NOT EXISTS books (
    id VARCHAR(10) PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    publisher VARCHAR(50) NOT NULL,
    price INT NOT NULL
);