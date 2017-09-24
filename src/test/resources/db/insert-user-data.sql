DELETE FROM bookstore.roles;
INSERT INTO bookstore.roles VALUES(0, 'USER');

DELETE FROM bookstore.users;
INSERT INTO bookstore.users VALUES (0, 'ALLOWED_USERNAME', '12345', 'text@gmail.com');