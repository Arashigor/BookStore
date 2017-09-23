CREATE SCHEMA bookstore;
CREATE TABLE bookstore.books (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30),
  author VARCHAR(30),
  year INT,
  genre VARCHAR(30)
);
CREATE TABLE bookstore.users (
  id int PRIMARY KEY AUTO_INCREMENT,
  login VARCHAR(30),
  password VARCHAR(60),
  email VARCHAR(255)
);
CREATE TABLE bookstore.roles (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(25)
);