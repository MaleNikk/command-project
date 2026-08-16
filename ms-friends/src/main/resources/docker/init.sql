-- Create schema
CREATE SCHEMA IF NOT EXISTS friends;

--Create table relationship--
CREATE TABLE IF NOT EXISTS relationship
(
    id BIGINT PRIMARY KEY NOT NULL,
    friend_1 VARCHAR(100) NOT NULL,
    current_status VARCHAR(15) NOT NULL,
    friend_2 VARCHAR(100) NOT NULL,
    previous_status VARCHAR(15) NOT NULL,
    rating INT DEFAULT 0,
    block_author VARCHAR(100) NOT NULL,
    time_register BIGINT,
    time_update BIGINT
);