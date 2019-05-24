-- This script needs to run only once

DROP DATABASE IF EXISTS INVENTORYDB;
CREATE DATABASE INVENTORYDB;

USE INVENTORYDB;

CREATE USER 'admin'@'localhost' IDENTIFIED VIA mysql_native_password USING '***';
GRANT ALL PRIVILEGES ON *.* TO 'admin'@'localhost' 
REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
GRANT ALL PRIVILEGES ON `inventorydb`.* TO 'admin'@'localhost';

FLUSH PRIVILEGES;
