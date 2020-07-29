create table movie(
                      ID varchar(9),
                      Title varchar(255),
                      mDescription varchar(1000),
                      dateOfRelease varchar(10),
                      MovieGenre varchar(511),
                      Duration int,
                      country varchar(255),
                      Writers varchar(511),
                      worldgross varchar(20),
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


