use movieapp;

-- Reformat Place to match the csv
DROP TABLE Place;
-- ALTER TABLE Place ADD COLUMN Country VARCHAR(2), ADD COLUMN City VARCHAR(255), ADD COLUMN AccentCity VARCHAR(255), ADD COLUMN Region VARCHAR(2), ADD COLUMN Latitude double, ADD COLUMN Longitude double, ADD PRIMARY KEY(City, Country, Region);
create table Place(Country VARCHAR(255),
                   City VARCHAR(255),
                   Region VARCHAR(255),
                   Population VARCHAR(255),
                   Latitude VARCHAR(255),
                   Longitude VARCHAR(255)
);



-- LOAD PLACE
-- ALTER TABLE place ALTER Population SET DEFAULT 0;
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/worldcitiesCleaned.csv' INTO TABLE place CHARACTER SET 'utf8'
    FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n' IGNORE 1 LINES;

-- Lowest cardinality
create table distinctPlace
SELECT DISTINCT Place.City, Place.Country
FROM Place;

-- Highest cardinality
create table distinctLocation
SELECT DISTINCT Place.City, Place.Country, Place.Latitude, Place.Longitude
FROM Place;

-- Confirmation that only distinct country codes are left, ~240 something which seems right
create table distinctCountry
SELECT DISTINCT Place.Country
FROM Place;

-- Matches lowest cardinality
create table  distinctPlaceWithCoord
SElECT Country, City, AVG(Latitude), AVG(Longitude)
FROM  PLACE
GROUP BY City, Country;
-- Make the primary key in place of Place (mainly for loading data)
ALTER TABLE distinctPlaceWithCoord ADD PRIMARY KEY(City,Country);
