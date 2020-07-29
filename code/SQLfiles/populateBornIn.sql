-- this one is going to be super interesting because we are joining two different data sets
-- first step is to look through and connect the place and where the people were born in
-- second step is to create a new location table that only have the places where people are born because
-- all the other data is either a repeat or never used so remove that
-- third step, add values to the new place table that the born in place was here
-- note: a person is only born in one place so place does not have to be a key
-- note: the born in is different from contry ex born in has USA where country has US so we need to figure out how to do this
-- note: this table is not complete so we have to see what happens above


-- Create
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

LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/CountryAbbreviations.csv' INTO TABLE CountryAbbrev
    FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';

-- FIND MATCHES
SELECT c.name
FROM CountryAbbrev c, place p
WHERE p.Country = c.name;


-- Alpha 2 matches the country code of distinctplacewithcoord.Country
-- Alpha 3 or country name matches the country name in person.Country
drop table  Bornin;
Create table Bornin
Select A.ActorID, D.City, D.Country
From Person A, distinctplacewithcoord D, CountryAbbrev C
WHERE lower(D.City) = lower(A.City) AND lower(D.Country) = lower(C.alpha2) AND (lower(A.Country) = lower(C.name) OR lower(A.Country) = lower(C.alpha3));

drop table Bornin;
create table Bornin
Select A.ActorID, D.City, D.Country
From Person A, distinctplacewithcoord D
WHERE D.City = lower(A.City);
-- FOR TESTING
create table DistinctAndAbbrev
Select D.City, D.Country
From distinctplacewithcoord D, CountryAbbrev C
WHERE D.Country = lower(C.alpha2);
-- FOR TESTING
drop table PersonAndAbbrev;
create table PersonAndAbbrev
Select p.City, p.Country
FROM Person p, CountryAbbrev c
WHERE TRIM(lower(p.Country)) = lower(c.name);

-- WORKED, 30k entries (out of 174k)
drop table PersonAndPlace;
create table PersonAndPlace
select pr.ActorID, pl.City, pl.Country
from person pr, place pl
where lower(pr.City) = pl.City;
