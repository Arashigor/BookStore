CREATE SCHEMA bookstore;
CREATE TABLE bookstore.books (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30),
  author VARCHAR(30),
  year INT,
  genre VARCHAR(30)
);