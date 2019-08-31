--DROP SCHEMA ecommerce CASCADE;
--CREATE DATABASE local;

--ALTER DATABASE local OWNER TO postgres;

--CREATE SCHEMA IF NOT EXISTS ecommerce AUTHORIZATION postgres;

DROP TABLE IF EXISTS ecommerce.address;
CREATE TABLE ecommerce.address(
	id INTEGER PRIMARY KEY,
	street VARCHAR,
	num INTEGER,
	extra VARCHAR,
	zip_code VARCHAR,
	state VARCHAR(60),
	city VARCHAR(60),
	country varchar(50)
);


DROP TABLE IF EXISTS ecommerce.user;
CREATE TABLE  ecommerce.user(
	id INTEGER PRIMARY KEY,
	username VARCHAR UNIQUE NOT NULL,
	password VARCHAR NOT NULL,
	address_id INTEGER,
	FOREIGN KEY (address_id) REFERENCES ecommerce.address (id)
);

DROP TABLE IF EXISTS ecommerce.product;
CREATE TABLE ecommerce.product(
	sku INTEGER PRIMARY KEY,
	name VARCHAR,
	price DOUBLE PRECISION
);

DROP TABLE IF EXISTS ecommerce.order;
CREATE TABLE  ecommerce.order(
	id SERIAL PRIMARY KEY,
	create_date DATE NOT NULL,
	total DOUBLE PRECISION,
	user_id INTEGER,
	FOREIGN KEY (user_id) REFERENCES ecommerce.user (id)
);

DROP TABLE IF EXISTS ecommerce.item;
CREATE TABLE ecommerce.item(
	id SERIAL PRIMARY KEY,
	quantity INTEGER NOT NULL,
	product_id INTEGER,
	order_id INTEGER, 
	FOREIGN KEY (product_id) REFERENCES ecommerce.product (sku),
	FOREIGN KEY (order_id) REFERENCES ecommerce.order (id)
);

INSERT INTO ecommerce.product(sku, name, price) VALUES (1,'Produto A', 10.0);
INSERT INTO ecommerce.product(sku, name, price) VALUES (2,'Produto B', 15.0);
INSERT INTO ecommerce.product(sku, name, price) VALUES (3,'Produto C', 30.0);

INSERT INTO ecommerce.address(id, street, num, extra, zip_code, city, state, country)
	VALUES (1, 'Av. Santa Catarina', 1130, 'Apto 602', '88070-740', 'Florianópolis','SC','BR');
INSERT INTO ecommerce.address(id, street, num, extra, zip_code, city, state, country)
	VALUES (2, 'Rua Lauro Linhares', 465, 'Apto 501', '88240-080', 'Florianópolis','SC','BR');

INSERT INTO ecommerce.user(id, username, password, address_id) VALUES(1, 'gustavo.souza','123', 1);
INSERT INTO ecommerce.user(id, username, password, address_id) VALUES(2, 'vitor.gomes','321', 2);

