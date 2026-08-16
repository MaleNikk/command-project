-- create db for account service;
CREATE DATABASE account_db;

-- create db for dialogs service
CREATE DATABASE dialogs_db;

-- create db for friends service
CREATE DATABASE friends_db;

-- create db for post service;
CREATE DATABASE post_db;

-- create db for notifications service
CREATE DATABASE notifications_db;

-- create db for geo service;
CREATE DATABASE geo_db;

-- connect to geo_db
\c geo_db;

CREATE SCHEMA IF NOT EXISTS 'location';

CREATE TABLE IF NOT EXISTS location.cities
(
id SERIAL PRIMARY KEY,
city_id UUID NOT NULL,
is_deleted BOOLEAN DEFAULT FALSE,
city_title VARCHAR(85) NOT NULL,
country_id UUID NOT NULL,
country_title VARCHAR(85) NOT NULL
);

CREATE TABLE IF NOT EXISTS location.countries
(
id SERIAL PRIMARY KEY,
country_id UUID NOT NULL,
is_deleted BOOLEAN DEFAULT FALSE,
country_title VARCHAR(85) NOT NULL
);

-- connect to account_db
\c account_db;

-- create schema
CREATE SCHEMA IF NOT EXISTS person;

-- connect to dialogs_db
\c dialogs_db;

-- create schema
CREATE SCHEMA IF NOT EXISTS chat;

--Create table dialog--
CREATE TABLE IF NOT EXISTS chat.dialog
(
dialog_id VARCHAR(100) PRIMARY KEY NOT NULL,
person_id_1 VARCHAR(150) NOT NULL,
person_id_2 VARCHAR(150) NOT NULL,
created_date VARCHAR(100) NOT NULL,
deleted BOOLEAN DEFAULT FALSE
);

--Create table message--
CREATE TABLE IF NOT EXISTS chat.message
(
message_id VARCHAR(100) PRIMARY KEY NOT NULL,
dialog_id VARCHAR(100) NOT NULL,
author_id VARCHAR(150) NOT NULL,
message VARCHAR(500) NOT NULL,
status VARCHAR(10) NOT NULL,
send_date VARCHAR(100) NOT NULL,
update_date VARCHAR(100),
deleted BOOLEAN DEFAULT FALSE
);

--Create table editable--
CREATE TABLE IF NOT EXISTS chat.editable
(
message_id VARCHAR(100) PRIMARY KEY NOT NULL,
dialog_id VARCHAR(100) NOT NULL,
author_id VARCHAR(150) NOT NULL,
message VARCHAR(500) NOT NULL,
status VARCHAR(10) NOT NULL,
send_date VARCHAR(100) NOT NULL,
update_date VARCHAR(100),
deleted BOOLEAN DEFAULT FALSE
);

-- Возврат к базе postgres
\c postgres;

-- Вывод информации о созданных базах
SELECT datname FROM pg_database WHERE datname IN ('account_db', 'dialogs_db', 'friends_db', 'post_db', 'notifications_db', 'geo_db');