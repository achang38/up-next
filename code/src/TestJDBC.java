import java.util.ArrayList;
import java.sql.*;

/**
 * This class uses the ConnectorJ driver to connect to MySQL database and interact with it
 */
public class TestJDBC {
	//jdbc:mysql://localhost:3306/movieapp
    static final String databasePrefix ="movieapp";//enter database
    static final String SQLlogin ="root"; // TODO change if pulled
    static final String hostName ="localhost:3306"; //TODO CHANGE if you've just pulled
    static final String databaseURL ="jdbc:mysql://"+hostName+"/"+databasePrefix+"?autoReconnect=true&useSSL=false";
    static final String SQLpassword=""; // enter password TODO CHANGE if you've just pulled
    private Connection connection = null;
    private Statement statement = null;
	private ResultSet resultSet = null;

	//stores column names for age ratings
	private static String[] agecolumns = {"avg0","avg18","avg30","avg45"};
   
	
	//Connect to the database
    public void Connection(){
  
      try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("databaseURL"+ databaseURL);
            connection = DriverManager.getConnection(databaseURL, SQLlogin, SQLpassword);
            System.out.println("Successfully connected to the database");
         }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	//Given a movieID, return a list of that movies attributes
	public ArrayList<Object> getMovieInfo(String movieID) {
		ArrayList<Object> mInfo = new ArrayList<Object>();
		try {
			statement = connection.createStatement();
    		resultSet = statement.executeQuery("select * from movie where id='"+movieID+"';");

    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();


    		while (resultSet.next()) {
       
    			for (int i=1; i<= columns; i++) {
					mInfo.add(resultSet.getObject(i));
					//create table movie(ID, Title, dateOfRelease, MovieGenre, Duration, country , Writers, worldgross, Director, mLanguage, listofactors
    			}

    		}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return mInfo;
	}
	
	//given a username and password, check if it is valid for the database
    public boolean verifyLogin(String username, String password) {
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select * from ProgramUser where UserName='"+username+"' and UserPassword='"+password+"';" );
    		
    		if(!resultSet.next()) {
    			return false;
    		} else {
    	
    			return true;
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
	
	//check if a username has already been taken
    public boolean verifyNewUser(String username) {
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select * from ProgramUser where UserName='"+username+"';" );
    		
    		if(!resultSet.next()) {
    			return true;
    		} else {
    			
    			return false;
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	return false;
    }
	
	//add a new user to the Programuser table
    public void addNewUser(String username, String password, String name, int age) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into ProgramUser(UserPassword,UserName,RealName,Age) values('"+password+"','"+username+"','"+name+"',"+age+");");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    }
	
	//search for a movie by name and return all results in Pairs holding movie name and id
    public ArrayList<Pair> searchMovie(String movie) {
    	ArrayList<Pair> movies = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select title,ID from movie where title like '%"+movie+"%';");

    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();


    		while (resultSet.next()) {
       
    			for (int i=1; i<= columns; i++) {
    				
    			}
    			Pair p = new Pair((String)resultSet.getObject(2),(String)resultSet.getObject(1));
    			
    			movies.add(p);
    			
    			
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return movies;
	}
	
	//return list of all liked movies by a user
	public ArrayList<Pair> likedMovie(String password,String user) {
    	ArrayList<Pair> movies = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select m.title, m.ID from movie m, likedmovie l where m.id=l.id and l.username='"+user+"' and l.userpassword='"+password+"';");


    		while (resultSet.next()) {
       
    			Pair p = new Pair((String)resultSet.getObject(2),(String)resultSet.getObject(1));
    			
    			movies.add(p);
    			
    		
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return movies;
	}

	//return list of all liked people of a user
	public ArrayList<Pair> likedPeople(String password,String user) {
    	ArrayList<Pair> people = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select p.castname, p.actorID from person p, likedpeople l where p.actorid=l.actorid and l.username='"+user+"' and l.userpassword='"+password+"';");


    		while (resultSet.next()) {
    
    			Pair p = new Pair((String)resultSet.getObject(2),(String)resultSet.getObject(1));
    		
    			people.add(p);
    			
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return people;
	}

	//get the favorite movie of a user in a pair arraylist
	public ArrayList<Pair> favoriteMovie(String password,String user) {
    	ArrayList<Pair> movies = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select m.title, m.ID from movie m, favoritemovie l where m.id=l.id and l.username='"+user+"' and l.userpassword='"+password+"';");


    		while (resultSet.next()) {
       
    			Pair p = new Pair((String)resultSet.getObject(2),(String)resultSet.getObject(1));
    			movies.add(p);
    			

    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return movies;
	}

	//get favorite person a user
	public ArrayList<Pair> favoritePerson(String password,String user) {
    	ArrayList<Pair> people = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select p.castname, p.actorID from person p, favoriteperson l where p.actorid=l.actorid and l.username='"+user+"' and l.userpassword='"+password+"';");


    		while (resultSet.next()) {
       
    			Pair p = new Pair((String)resultSet.getObject(2),(String)resultSet.getObject(1));

    			people.add(p);
    			

    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return people;
	}
	
	//find movies that contain an actor that user likes, that are not already in likedmovies, dislikedmovies, or favoritemovie
	//order by rating for age index
	public ArrayList<Pair> simActorMovie(String password,String user, int ageIndex) {
		ArrayList<Pair> movies = new ArrayList<Pair>();
		String agecolumn = agecolumns[ageIndex];
		

    	try {
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select distinct m.title, m.id from movie m, actedin a where m.id not in "+
			"(select id from likedmovie where username='"+user+"' and userpassword='"+password+
			"' union select id from dislikedmovie where username='"+user+"' and userpassword='"+password+
			"' union select id from favoritemovie where username='"+user+"' and userpassword='"+password+"') "+
			"and a.id=m.id and a.actorid in (select actorid from likedpeople where username='"+user+
			"' and userpassword='"+password+"' union select actorid from favoriteperson where username='"+user+
			"' and userpassword='"+password+"') order by "+agecolumn+" desc limit 15;");


    		while (resultSet.next()) {
       
    			Pair p = new Pair((String)resultSet.getObject(2),(String)resultSet.getObject(1));
    
    			movies.add(p);
    			
    	
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return movies;
	}

	//find movies that match the genre of users favorite movie
	public ArrayList<Pair> simGenreMovie(String password,String user,int ageIndex) {
		ArrayList<Pair> movies = new ArrayList<Pair>();
		String agecolumn = agecolumns[ageIndex];
		

    	try {
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select m.title, m.id from movie m where m.moviegenre in"+
			"(select m2.moviegenre from favoritemovie f, movie m2 where f.username='"+user+"' and f.userpassword='"+password+
			"' and f.id=m2.id) and m.id not in (select id from likedmovie where username='"+user+"' and userpassword='"+password+
			"' union select id from dislikedmovie where username='"+user+"' and userpassword='"+password+
			"' union select id from favoritemovie where username='"+user+"' and userpassword='"+password+"') order by "+agecolumn+" desc limit 15; ");



    		while (resultSet.next()) {
       
 
    			Pair p = new Pair((String)resultSet.getObject(2),(String)resultSet.getObject(1));

    			movies.add(p);
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return movies;
	}

	//find movies that are directed by someone a user likes
	public ArrayList<Pair> simDirMovie(String password,String user,int ageIndex) {
		ArrayList<Pair> movies = new ArrayList<Pair>();
		String agecolumn = agecolumns[ageIndex];

    	try {
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select distinct m.title, m.id from movie m, directed a where m.id not in "+
			"(select id from likedmovie where username='"+user+"' and userpassword='"+password+
			"' union select id from dislikedmovie where username='"+user+"' and userpassword='"+password+
			"' union select id from favoritemovie where username='"+user+"' and userpassword='"+password+"') "+
			"and a.id=m.id and a.actorid in (select actorid from likedpeople where username='"+user+
			"' and userpassword='"+password+"' union select actorid from favoriteperson where username='"+user+
			"' and userpassword='"+password+"') order by "+agecolumn+" desc limit 15;");


    		while (resultSet.next()) {
       
    			Pair p = new Pair((String)resultSet.getObject(2),(String)resultSet.getObject(1));

    			movies.add(p);
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return movies;
	}

	//find movies that are directed and contain an actor that user likes
	public ArrayList<Pair> simAllMovie(String password,String user, int ageIndex) {
		ArrayList<Pair> movies = new ArrayList<Pair>();
		String agecolumn = agecolumns[ageIndex];

    	try {
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select distinct m.title, m.id from movie m, directed a, actedin a2 where m.id not in "+
			"(select id from likedmovie where username='"+user+"' and userpassword='"+password+
			"' union select id from dislikedmovie where username='"+user+"' and userpassword='"+password+
			"' union select id from favoritemovie where username='"+user+"' and userpassword='"+password+"') "+
			"and a.id=m.id and a.actorid in (select actorid from likedpeople where username='"+user+
			"' and userpassword='"+password+"' union select actorid from favoriteperson where username='"+user+
			"' and userpassword='"+password+"') and a2.id=m.id and a2.actorid in (select actorid from likedpeople where username='"+user+
			"' and userpassword='"+password+"' union select actorid from favoriteperson where username='"+user+
			"' and userpassword='"+password+"') order by "+agecolumn+" desc limit 15;");



    		while (resultSet.next()) {
       
    			Pair p = new Pair((String)resultSet.getObject(2),(String)resultSet.getObject(1));
   
    			movies.add(p);
    			
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return movies;
	}

	//search for people that match String person in the Person table
    public ArrayList<Pair> searchPeople(String person) {
    	ArrayList<Pair> people = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select castname,actorid from person where castname like '%"+person+"%';");

    		while (resultSet.next()) {
       
    			
    			Pair p = new Pair((String)resultSet.getObject(2),(String)resultSet.getObject(1));

    			people.add(p);

    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return people;
	}

	//get all actors in movie by their id, cast name
	public ArrayList<Pair> getActors(String movieID) {
    	ArrayList<Pair> people = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
			resultSet = statement.executeQuery("select p.castname,p.actorid from person p, actedin a where p.actorid=a.actorid and a.id='"
			+movieID+"';");

    		while (resultSet.next()) {
       
    			
    			Pair p = new Pair((String)resultSet.getObject(2),(String)resultSet.getObject(1));
    		
    			people.add(p);
    			
    	
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return people;
	}

	//get directors of movie by their id, castname
	public ArrayList<Pair> getDirectors(String person) {
    	ArrayList<Pair> people = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
			resultSet = statement.executeQuery("select p.castname,p.actorid from person p, directed a where p.actorid=a.actorid and a.id='"
			+person+"';");


    		while (resultSet.next()) {
       
    			
    			Pair p = new Pair((String)resultSet.getObject(2),(String)resultSet.getObject(1));
    			
    			people.add(p);
    			
    		}
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    	return people;
	}
	
    //add to user liked list
    public void addLikedMovie(String password, String user, String id) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into LikedMovie(UserPassword,UserName,ID) values('"+password+"','"+user+"','"+id+"');");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}
	
	//add to user disliked list
	public void addDislikedMovie(String password, String user, String id) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into DislikedMovie(UserPassword,UserName,ID) values('"+password+"','"+user+"','"+id+"');");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
	
	//add to user liked list of people
    public void addLikedPerson(String password, String user, String id) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into LikedPeople(UserPassword,UserName,ActorID) values('"+password+"','"+user+"','"+id+"');");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }

	//update the favorite movie of user
    public void addFavMovie(String password, String user, String id) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into FavoriteMovie(UserPassword,UserName,ID) values('"+password+"','"+user+"','"+id+"');");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }

	//update favorite person of user
    public void addFavPerson(String password, String user, String id) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into FavoritePerson(UserPassword,UserName,ActorID) values('"+password+"','"+user+"','"+id+"');");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}
	
	//change username of user
	public void changeUsername(String newuser, String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("update ProgramUser set UserName='"+newuser+"' where UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	//change password of user
	public void changePassword(String newpass, String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("update ProgramUser set UserPassword='"+newpass+"' where UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	//change age of user
	public void changeAge(int newage, String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("update ProgramUser set age="+newage+" where UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	//change real name attribute of user
	public void changeName(String newname, String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("update ProgramUser set RealName='"+newname+"' where UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	//find the age index based off of users age
	public int getAgeIndex(int age) {
		if(age<18) return 0;
		if(age<30) return 1;
		if(age<45) return 2;
		return 3;
	}

	//get user real name
	public String getRealName(String user, String password) {
		try {
    			statement = connection.createStatement();
    			resultSet = statement.executeQuery("select realname from programuser where username='"+user+"' and userpassword='"+password+"';");
				
				resultSet.next();
				return (String)resultSet.getObject(1);
				
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
		}
		return "";
	}

	//get user age as string
	public String getAge(String user, String password) {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("select age from programuser where username='"+user+"' and userpassword='"+password+"';");
			resultSet.next();

			return String.valueOf(resultSet.getObject(1));

	}
	catch (SQLException e) {
		e.printStackTrace();
	}
	return "";
	}

	//remove liked movie from user list
	public void removeLikedMovie(String movieID, String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("delete from LikedMovie where ID='"+movieID+"' and UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	//remove liked person from user list
	public void removeLikedPerson(String actorID, String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("delete from LikedPeople where actorID='"+actorID+"' and UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	//remove favorite movie of user
	public void removeFavMovie(String movieID, String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("delete from FavoriteMovie where ID='"+movieID+"' and UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	//remove favorite person of user
	public void removeFavPerson(String actorID, String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("delete from FavoritePerson where actorID='"+actorID+"' and UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	//delete users account
	public void deleteUser(String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("delete from ProgramUser where UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	public ArrayList<Double> getCoordinates(String actorID){
    	ArrayList<Double> coordPair = new ArrayList<Double>();

    	try{
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("Select d.Latitude, d.Longitude FROM distinctplacewithcoord d, bornin b WHERE " +
					"b.ActorID='" + actorID +"'AND b.City = d.City AND b.Country = d.Country;");

    		if(resultSet.next()) {
				System.out.println(resultSet.getObject(1));
				System.out.println(resultSet.getObject(2));
//    		System.out.println(resultSet.getDouble(1));
//			System.out.println(resultSet.getDouble(2));

				coordPair.add(resultSet.getDouble(1));
				coordPair.add(resultSet.getDouble(2));
				return coordPair;
			}else{
    			System.out.println("The result set was null");
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return null;
	}
	
	//This main method is to simply test that queries work
    public static void main(String args[]) {

    	TestJDBC demoObj = new TestJDBC();
		demoObj.Connection();
		
    }
    
}
