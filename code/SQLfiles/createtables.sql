create table movie(
                      ID varchar(9),
                      Title varchar(255),
                      mDescription varchar(1000),
                      dateOfRelease varchar(10),
                      MovieGenre varchar(511),
                      Duration int,
                      country varchar(255),
                      Writers varchar(511),
                      worldgross varchar(255),
                      Director varchar(255),
                      mLanguage varchar(255),
                      listofactors varchar(1000),
                      avgtotal decimal(10,1),
                      avg0 decimal(10,1),
                      avg18 decimal(10,1),
                      avg30 decimal(10,1),
                      avg45 decimal(10,1),
                      primary key(ID));

create table person(
                       ActorID varchar(16),
                       CastName varchar(255),
                       Birthday  varchar(16),
                       MainOccupation varchar(255),
                       KnownForTitles varchar(8000),
                       City varchar(255),
                       Country varchar(255),
                       primary key(ActorID)
);

create table Place(Country VARCHAR(255),
                   City VARCHAR(255),
                   Region VARCHAR(255),
                   Population VARCHAR(255),
                   Latitude VARCHAR(255),
                   Longitude VARCHAR(255)
);

create table CountryAbbrev (
                               name varchar(255),
                               alpha2 varchar(255),
                               alpha3 varchar(255),
                               countrycode varchar(255),
                               iso_31662 varchar(255),
                               region varchar(255),
                               subregion varchar(255),
                               intermediateregion varchar(255),
                               regioncode varchar(255),
                               subregioncode varchar(255),
                               intermediateregioncode varchar(255),
                               primary key(name)
);

create table ProgramUser(Userpassword varchar(255), Username varchar(255), realname varchar(255), age int, Primary key(userpassword, username));

Create Table FavoritePerson(UserPassword varchar(255), UserName varchar(255), ActorID varchar(9), Primary key(UserPassword,UserName), FOREIGN KEY (UserPassword, UserName) REFERENCES ProgramUser(UserPassword, UserName) ON UPDATE cascade ON DELETE cascade, Foreign key (ActorID) references person(ActorID));
-- they all need to be forgin keys becuase to be in the realtionship they must be in the tables already
Create Table LikedPeople(UserPassword varchar(255), UserName varchar(255), ActorID varchar(9), Primary key(UserPassword,Username,ActorID), FOREIGN KEY (UserPassword, UserName) REFERENCES ProgramUser(UserPassword, UserName) ON UPDATE cascade ON DELETE cascade, FOREIGN KEY (ActorID) REFERENCES person(ActorID));
-- the ID does not need to be part of the key becuase each user only has one favorit movie
Create Table FavoriteMovie(UserPassword varchar(255), UserName varchar(255), ID varchar(9), primary key(UserPassword,Username), FOREIGN KEY (UserPassword, UserName) REFERENCES ProgramUser(UserPassword, UserName) ON update cascade ON delete cascade, FOREIGN KEY(ID) references movie(ID));
-- all need to forign keys becuase they have to be there to be in the realtionsip
Create Table LikedMovie(UserPassword varchar(255), UserName varchar(255), ID varchar(9), primary key(Userpassword,Username,ID), FOREIGN KEY (UserPassword, UserName) REFERENCES ProgramUser(UserPassword, UserName) ON update cascade ON delete cascade, FOREIGN KEY (ID) REFERENCES movie(ID));

Create Table DislikedMovie(UserPassword varchar(255), UserName varchar(255), ID varchar(9), primary key(Userpassword,Username,ID),FOREIGN KEY (UserPassword, UserName) REFERENCES ProgramUser(UserPassword, UserName) ON update cascade ON delete cascade, FOREIGN KEY (ID) REFERENCES movie(ID));

Create Table ActedIn(ID varchar(9), actorID varchar(16), primary key(ID,actorID));

Create Table Directed(ID varchar(9), actorID varchar(16), primary key(ID,actorID));

Create Table DistinctPlaceWithCoord(Country varchar(2), City varchar(255), Latitude DOUBLE, Longitude DOUBLE);

Create Table BornIn(ActorID varchar(16), City VARCHAR(255), Country VARCHAR(2));

/** ORIGINALLY USED A TABLE THAT FINDS DISTINCT VALUES FROM PLACE SINCE THERE IS NO PRIMARY KEY FOR PLACE.
    THIS WAS INTENTIONAL AS HAVING A PRIMARY KEY RESULTED IN DUPLICATE ERRORS DUE TO AN ISSUE WITH
    SPECIAL UNICODE CHARACTERS NOT BEING DIFFERENTIATED FROM ASCII VALUES.
    We are no longer implementing them as dependent on other table with a select/from/where,
    but loading from a cleaned csv as a result of formerly performing this operation.
create table  distinctPlaceWithCoord
SElECT Country, City, AVG(Latitude), AVG(Longitude)
FROM  PLACE
GROUP BY City, Country;
-- Make the primary key in place of Place (mainly for loading data)
ALTER TABLE distinctPlaceWithCoord ADD PRIMARY KEY(City,Country);

create table Bornin
Select A.ActorID, D.City, D.Country
From Person A, distinctplacewithcoord D
WHERE D.City = lower(A.City)

 */

