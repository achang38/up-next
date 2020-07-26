use movieapp;

-- Reformat Place to match the csv
DROP TABLE Place;
-- ALTER TABLE Place ADD COLUMN Country VARCHAR(2), ADD COLUMN City VARCHAR(255), ADD COLUMN AccentCity VARCHAR(255), ADD COLUMN Region VARCHAR(2), ADD COLUMN Latitude double, ADD COLUMN Longitude double, ADD PRIMARY KEY(City, Country, Region);
create table Place(Country VARCHAR(2),
                   City VARCHAR(255),
                   AccentCity VARCHAR(255),
                   Region VARCHAR(2),
                   Population BIGINT,
                   Latitude double,
                   Longitude double,
                   PRIMARY KEY(City, Country, Region)
);

-- LOAD PLACE
ALTER TABLE place ALTER Population SET DEFAULT 0;
LOAD DATA INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/worldcitiespopRef.csv' INTO TABLE place
    FIELDS TERMINATED BY ',' ENCLOSED BY '"' LINES TERMINATED BY '\n';



