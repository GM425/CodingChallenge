CREATE DATABASE IF NOT EXISTS EMPLOYEE_DB;

USE EMPLOYEE_DB;

CREATE TABLE IF NOT EXISTS employee_data (

	id INT NOT NULL AUTO_INCREMENT,
	username VARCHAR(50),
	firstname VARCHAR(50),
	lastname VARCHAR(50),
	streetaddress VARCHAR(50),
	city VARCHAR(50),
	state VARCHAR(50),
	zip INT,
	homephone VARCHAR(50),
	cellphone VARCHAR(50),
	email VARCHAR(50),
    PRIMARY KEY (id)
);

INSERT INTO employee_data(username, firstName, lastName, streetAddress, city, state, zip, homePhone, cellPhone, email)
VALUES("10", "david", "allen", "123 Main Street", "Cleveland", "Ohio", 23567, "3158903344", "3158903344", "allen@gmail.com");
-- GRANT ALL PRIVILEGES ON employee_data.* TO 'user' IDENTIFIED BY 'user';
-- FLUSH PRIVILEGES;