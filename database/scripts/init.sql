IF DB_ID('trader_db') IS NULL
  CREATE DATABASE trader_db;
  GO
  USE trader_db;
  GO
  CREATE SCHEMA tdb;
  GO
  CREATE TABLE players (
    id INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL
  );
  GO
  INSERT INTO players (id, username) VALUES
  (1,'Player1'),
  (2,'Player2'),
  (3,'Player3'),
  (4,'Player4'),
  (5,'Player5');
  GO
