use movieapp;
-- Reformat movie DESCRIPTION added
-- DROP TABLE likedmovie;
DROP TABLE movie;
create table movie(
                      ID varchar(9),
                      Title varchar(255),
                      Description varchar(1000),
                      dateOfRelease varchar(10),
                      MovieGenre varchar(511),
                      Duration int,
                      country varchar(255),
                      Writers varchar(511),
                      worldgross BIGINT,
                      Director varchar(255),
                      mLanguage varchar(255),
                      listofactors varchar(8000),
                      primary key(ID));
-- Reformat Person, ID must be 10 not 9
-- drop table likedpeople;
/**create table person(
    ActorID varchar(10),
        CastName varchar(255),
         Height double,
          Birthday  varchar(10),
           PlaceOfBirth varchar(255),
            DateOfDeath  varchar(10),
             MainOccupation varchar(255),
              KnownForTitles varchar(255),
               primary key(ActorID)
                   );
 */

-- LOAD MOVIE
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/IMDb moviesRef.csv' INTO TABLE movie
    FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';