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
  name VARCHAR(25) UNIQUE
);
CREATE TABLE bookstore.usersroles (
  user_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  CONSTRAINT pk PRIMARY KEY (user_id,role_id),
  CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES bookstore.roles(id) ON DELETE CASCADE,
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES bookstore.users(id) ON DELETE CASCADE
);
CREATE TABLE bookstore.usersbooks(
  user_id INTEGER,
  book_id INTEGER,
  CONSTRAINT pk_ub PRIMARY KEY (user_id,book_id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES bookstore.users(id) ON DELETE CASCADE,
  CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES bookstore.books(id) ON DELETE CASCADE
);