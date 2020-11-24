drop user if exists 'user'@'localhost';
Create user 'user'@'localhost' identified by 'user123';
grant all privileges on ecommercestoredb.* to 'user'@'localhost';
flush privileges;

drop database if exists ecommercestoredb;
create database ecommercestoredb;