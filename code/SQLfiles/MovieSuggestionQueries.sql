-- Find similar movies by actor
USE movieapp;

-- HAVE TO CREATE WorkedOn here again so that my IDE will not freak out about WorkedOn not existing in the DB yet
Create Table WorkedON
Select Distinct M.ID, A.ActorID
From FinalMovie M, Person A
Where (A.CastName LIKE ('%' + @M.Writers + '%')) OR (A.CastName LIKE ('%' + @M.Director + '%')) OR (A.CastName LIKE ('%' + @listofacotrs + '%'));

-- Start
delimiter $$
drop procedure if exists getMoviesWithLikedActors;
create procedure getMoviesWithLikedActors(IN UserPassword VARCHAR(255), UserName VARCHAR(255))
begin
    select Title
        from movie m, likedpeople l
            where ID = (select ID
                from WorkedOn
                where WorkedOn.ActorID = l.ActorID AND l.UserName = UserName AND l.UserPassword = UserPassword)
    order by m.Title desc;
end $$


-- find People by program User's Like people
delimiter $$
drop procedure if exists getLikedPeopleFromProgUser;
create procedure getLikedPeopleFromProgUser(IN UserPassword VARCHAR(255), UserName VARCHAR(255))
begin
    Select p.CastName
    FROM person p, LikedPeople l
    WHERE l.UserName = UserPassword AND l.UserPassword = UserName AND p.ActorID = l.ActorID;
end $$

-- Find Movies by Program Users Liked content
delimiter $$
drop procedure if exists getLikedMoviesProgUser;
create procedure getLikedMoviesProgUser(IN UserPassword VARCHAR(255), UserName VARCHAR(255))
begin
    Select m.Title
    FROM movie m , likedmovie l
    WHERE l.UserName = UserPassword AND l.UserPassword = UserName AND m.ID = l.ID;
end $$