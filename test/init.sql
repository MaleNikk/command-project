-- Создание базы данных для Security Service
CREATE DATABASE security_service_db;

-- Создание базы данных для Account Service
CREATE DATABASE account_db;

-- create db for dialog service
CREATE DATABASE dialogs_db;

-- create db for friends service
CREATE DATABASE friends_db;

-- create database for geo-service
CREATE DATABASE geo_db;

-- create database for notifications-service
CREATE DATABASE notifications_db;

-- create database for auth-service
CREATE DATABASE auth_db;

-- connect to auth_db
\c auth_db;

-- Создание схемы news_schema
CREATE SCHEMA IF NOT EXISTS user_schema;

--Create table app_user--
CREATE TABLE IF NOT EXISTS user_schema.app_user
(
    id UUID PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

--Create table app_user--
CREATE TABLE IF NOT EXISTS user_schema.user_roles
(
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    role VARCHAR(255) NOT NULL
);


-- connect to notifications_db
\c notifications_db;

-- Create schema
CREATE SCHEMA IF NOT EXISTS notifications;

--Create table notification--
CREATE TABLE IF NOT EXISTS notifications.notification
(
    id UUID PRIMARY KEY NOT NULL,
    account_id UUID NOT NULL,
    content TEXT NOT NULL,
    notification_type VARCHAR(50) NOT NULL,
    sent_time DATE NOT NULL,
    receiver_id UUID NOT NULL,
    service_type VARCHAR(50) NOT NULL,
    event_id UUID NOT NULL,
    is_reed BOOLEAN DEFAULT FALSE
);

--Create table settings--
CREATE TABLE IF NOT EXISTS notifications.settings
(
    id UUID PRIMARY KEY NOT NULL,
    enable_notifications BOOLEAN DEFAULT TRUE,
    enable_post BOOLEAN DEFAULT TRUE,
    enable_post_comment BOOLEAN DEFAULT TRUE,
    enable_comment_comment BOOLEAN DEFAULT TRUE,
    enable_friend_request BOOLEAN DEFAULT TRUE,
    enable_friend_birthday BOOLEAN DEFAULT TRUE,
    enable_message BOOLEAN DEFAULT TRUE
);

-- connect to geo_db
\c geo_db;

CREATE SCHEMA IF NOT EXISTS location;

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
SELECT datname FROM pg_database WHERE datname IN ('security_service_db', 'account_db', 'dialogs_db', 'friends_db', 'geo_db', 'notifications_db');