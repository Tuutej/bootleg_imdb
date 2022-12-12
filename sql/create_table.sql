CREATE TABLE movie(
id integer NOT NULL AUTO_INCREMENT,
name varchar(40) not null,
director varchar(80) not null,
genre varchar(20) not null,
releaseYear int,
runtimeMinutes int,
boxOfficeUsd decimal(5,1),
primary key(id)
);
