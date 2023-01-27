CREATE TABLE subscribers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);
INSERT INTO subscribers (name, email) VALUES ('Hungry Joe', 'ImhungryJoe@whensdinner.com');