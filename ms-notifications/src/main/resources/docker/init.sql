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