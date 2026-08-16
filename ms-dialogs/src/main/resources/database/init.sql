-- Создание базы данных для Security Service
CREATE DATABASE security_service_db;

-- Создание базы данных для Account Service
CREATE DATABASE account_db;

-- create db for dialog service
CREATE DATABASE dialogs_db;

-- Подключение к базе данных security_service_db и создание схемы
\c security_service_db;
CREATE SCHEMA IF NOT EXISTS public;

-- Подключение к базе данных account_db и создание схемы
\c account_db;
CREATE SCHEMA IF NOT EXISTS public;
CREATE SCHEMA IF NOT EXISTS accountdb_schema;

-- connect to dialogs_db
\c dialogs_db;

-- Create data base
CREATE SCHEMA IF NOT EXISTS chat;

--Create table dialog--
CREATE TABLE IF NOT EXISTS dialog
(
    dialog_id VARCHAR(100) PRIMARY KEY NOT NULL,
    person_id_1 VARCHAR(150) NOT NULL,
    person_id_2 VARCHAR(150) NOT NULL,
    created_date VARCHAR(100) NOT NULL,
    deleted BOOLEAN DEFAULT FALSE
);

--Create table message--
CREATE TABLE IF NOT EXISTS message
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
CREATE TABLE IF NOT EXISTS editable
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
SELECT datname FROM pg_database WHERE datname IN ('security_service_db', 'account_db', 'dialogs_db');