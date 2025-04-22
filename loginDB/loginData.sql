CREATE DATABASE IF NOT EXISTS LOGIN_DB;

USE LOGIN_DB;

CREATE TABLE IF NOT EXISTS login_db (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(50),
    password VARCHAR(60),
    PRIMARY KEY (id)
);

INSERT INTO login_db (email, password) VALUES ("skol@gmail.com","dkjji3i3");
-- GRANT ALL PRIVILEGES ON employee_data.* TO 'user' IDENTIFIED BY 'user';
-- FLUSH PRIVILEGES;
