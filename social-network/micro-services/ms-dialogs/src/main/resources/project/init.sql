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