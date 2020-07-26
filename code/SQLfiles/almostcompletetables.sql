/**create database movieapp;*/
use movieapp;
create table person(ActorID varchar(9), CastName varchar(255), Height double, Birthday  varchar(10), PlaceOfBirth varchar(255), DateOfDeath  varchar(10), MainOccupation varchar(255), KnownForTitles varchar(255), primary key(ActorID));

create table movie(ID varchar(9), Title varchar(255), dateOfRelease varchar(10), MovieGenre varchar(511), Duration int, country varchar(255), Writers varchar(511), worldgross BIGINT, Director varchar(255), mLanguage varchar(255), listofactors varchar(8000), primary key(ID));
-- start with the original IMDB movies csv
-- reformatted to get rid of unused tables
-- Loading in formatted data
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/IMDb moviesRef.csv' INTO TABLE movie;

create table Place(CityName varchar(255), Country varchar(255), Region varchar(2), Population bigint, Longitude double, Latitude double, primary key(CityName, Country, Region));
-- we can not have 'user' as name of table because that is a key word same with password
create table ProgramUser(UserPassword varchar(255), UserName varchar(255), RealName varchar(255), primary key(UserPassword, Username));
ALTER TABLE ProgramUser ADD COLUMN Age BIGINT;
-- even tho demo and movie are the same entity they belong to two diffrent csv files, so i am going to join them on here
-- this table still needs some work done to it
create table demo(ID varchar(255), primary key(ID));

-- this will create a join between the demo and movie table csv's 
-- this table is complete
CREATE TABLE  FinalMovie
SELECT *
FROM demo
NATURAL JOIN movie;
-- Note: the following four are complete
-- the actor ID does not need to be a key because the user only has one favorite
Create Table FavoritePerson(UserPassword varchar(255), UserName varchar(255), ActorID varchar(9), FOREIGN KEY (UserPassword, UserName) REFERENCES ProgramUser(UserPassword, UserName));
-- they all need to be forgin keys becuase to be in the realtionship they must be in the tables already
Create Table LikedPeople(UserPassword varchar(255), UserName varchar(255), ActorID varchar(9), FOREIGN KEY (UserPassword, UserName) REFERENCES ProgramUser(UserPassword, UserName), FOREIGN KEY (ActorID) REFERENCES person(ActorID));
-- the ID does not need to be part of the key becuase each user only has one favorit movie
Create Table FavoriteMovie(UserPassword varchar(255), UserName varchar(255), ID varchar(9), FOREIGN KEY (UserPassword, UserName) REFERENCES ProgramUser(UserPassword, UserName));
-- all need to forign keys becuase they have to be there to be in the realtionsip
Create Table LikedMovie(UserPassword varchar(255), UserName varchar(255), ID varchar(9), FOREIGN KEY (UserPassword, UserName) REFERENCES ProgramUser(UserPassword, UserName), FOREIGN KEY (ID) REFERENCES movie(ID));

-- the movie csv has a list of actors/ writed / directors that we will pull from to create this 
-- realationship table 
-- note: a small probabillity (1.47%) of the actors have the same name. they will just be removed from the 
-- data set 
-- Note: this table is complete
Create Table WorkedON
Select Distinct M.ID, A.ActorID
From FinalMovie M, Person A 
Where (A.CastName LIKE ('%' + @M.Writers + '%')) OR (A.CastName LIKE ('%' + @M.Director + '%')) OR (A.CastName LIKE ('%' + @M.listofactors + '%'));

-- this one is going to be super interesting because we are joining two different data sets
-- first step is to look through and connect the place and where the people were born in
-- second step is to create a new location table that only have the places where people are born because
-- all the other data is either a repeat or never used so remove that
-- third step, add values to the new place table that the born in place was here
-- note: a person is only born in one place so place does not have to be a key
-- note: the born in is different from contry ex born in has USA where country has US so we need to figure out how to do this
-- note: this table is not complete so we have to see what happens above 
Create table Bornin
Select A.ActorID, P.CityName, P.Country, P.Region
From Person A, Place P
Where P.CityName like ('%' + @A.PlaceOfBirth + '%');


