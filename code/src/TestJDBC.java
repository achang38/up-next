import java.util.ArrayList;
import java.sql.*;

public class TestJDBC {
	//jdbc:mysql://localhost:3306/movieapp
    static final String databasePrefix ="movieapp2";//enter database
    static final String SQLlogin ="root"; // TODO change if pulled
    static final String hostName ="localhost:3306"; //TODO CHANGE if you've just pulled
    static final String databaseURL ="jdbc:mysql://"+hostName+"/"+databasePrefix+"?autoReconnect=true&useSSL=false";
    static final String SQLpassword="Musk$#eteer>3"; // enter password TODO CHANGE if you've just pulled
    private Connection connection = null;
    private Statement statement = null;
	private ResultSet resultSet = null;
	private static String[] agecolumns = {"avg0","avg18","avg30","avg45"};
   
    
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
    
    public void addNewUser(String username, String password, String name, int age) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into ProgramUser(UserPassword,UserName,RealName,Age) values('"+password+"','"+username+"','"+name+"',"+age+");");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	
    }
    
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
	
	
	public ArrayList<Pair> likedMovie(String password,String user) {
    	ArrayList<Pair> movies = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select m.title, m.ID from movie m, likedmovie l where m.id=l.id and l.username='"+user+"' and l.userpassword='"+password+"';");

    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();


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

	public ArrayList<Pair> likedPeople(String password,String user) {
    	ArrayList<Pair> people = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select p.castname, p.actorID from person p, likedpeople l where p.actorid=l.actorid and l.username='"+user+"' and l.userpassword='"+password+"';");

    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();


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

	public ArrayList<Pair> favoriteMovie(String password,String user) {
    	ArrayList<Pair> movies = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select m.title, m.ID from movie m, favoritemovie l where m.id=l.id and l.username='"+user+"' and l.userpassword='"+password+"';");

    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();

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

	public ArrayList<Pair> favoritePerson(String password,String user) {
    	ArrayList<Pair> people = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select p.castname, p.actorID from person p, favoriteperson l where p.actorid=l.actorid and l.username='"+user+"' and l.userpassword='"+password+"';");

    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();


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
	
	
	public ArrayList<Pair> simActorMovie(String password,String user, int ageIndex) {
		ArrayList<Pair> movies = new ArrayList<Pair>();
		String agecolumn = agecolumns[ageIndex];
		

    	try {
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select m.title, m.id from movie m, actedin a where m.id not in "+
			"(select id from likedmovie where username='"+user+"' and userpassword='"+password+
			"' union select id from dislikedmovie where username='"+user+"' and userpassword='"+password+
			"' union select id from favoritemovie where username='"+user+"' and userpassword='"+password+"') "+
			"and a.id=m.id and a.actorid in (select actorid from likedpeople where username='"+user+
			"' and userpassword='"+password+"' union select actorid from favoriteperson where username='"+user+
			"' and userpassword='"+password+"') order by "+agecolumn+" desc limit 15;");

			
    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();



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

			
    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();



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

	public ArrayList<Pair> simDirMovie(String password,String user,int ageIndex) {
		ArrayList<Pair> movies = new ArrayList<Pair>();
		String agecolumn = agecolumns[ageIndex];

    	try {
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select m.title, m.id from movie m, directed a where m.id not in "+
			"(select id from likedmovie where username='"+user+"' and userpassword='"+password+
			"' union select id from dislikedmovie where username='"+user+"' and userpassword='"+password+
			"' union select id from favoritemovie where username='"+user+"' and userpassword='"+password+"') "+
			"and a.id=m.id and a.actorid in (select actorid from likedpeople where username='"+user+
			"' and userpassword='"+password+"' union select actorid from favoriteperson where username='"+user+
			"' and userpassword='"+password+"') order by "+agecolumn+" desc limit 15;");

			
    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();

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

	public ArrayList<Pair> simAllMovie(String password,String user, int ageIndex) {
		ArrayList<Pair> movies = new ArrayList<Pair>();
		String agecolumn = agecolumns[ageIndex];

    	try {
			statement = connection.createStatement();
			
			resultSet = statement.executeQuery("select m.title, m.id from movie m, directed a, actedin a2 where m.id not in "+
			"(select id from likedmovie where username='"+user+"' and userpassword='"+password+
			"' union select id from dislikedmovie where username='"+user+"' and userpassword='"+password+
			"' union select id from favoritemovie where username='"+user+"' and userpassword='"+password+"') "+
			"and a.id=m.id and a.actorid in (select actorid from likedpeople where username='"+user+
			"' and userpassword='"+password+"' union select actorid from favoriteperson where username='"+user+
			"' and userpassword='"+password+"') and a2.id=m.id and a2.actorid in (select actorid from likedpeople where username='"+user+
			"' and userpassword='"+password+"' union select actorid from favoriteperson where username='"+user+
			"' and userpassword='"+password+"') order by "+agecolumn+" desc limit 15;");

			
    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();


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

    public ArrayList<Pair> searchPeople(String person) {
    	ArrayList<Pair> people = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
    		resultSet = statement.executeQuery("select castname,actorid from person where castname like '%"+person+"%';");

    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();

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

	public ArrayList<Pair> getActors(String movieID) {
    	ArrayList<Pair> people = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
			resultSet = statement.executeQuery("select p.castname,p.actorid from person p, actedin a where p.actorid=a.actorid and a.id='"
			+movieID+"';");

    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();

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

	public ArrayList<Pair> getDirectors(String person) {
    	ArrayList<Pair> people = new ArrayList<Pair>();
    	
    	try {
    		statement = connection.createStatement();
			resultSet = statement.executeQuery("select p.castname,p.actorid from person p, directed a where p.actorid=a.actorid and a.id='"
			+person+"';");

    		ResultSetMetaData metaData = resultSet.getMetaData();
    		int columns = metaData.getColumnCount();

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
	
    
    public void addLikedMovie(String password, String user, String id) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into LikedMovie(UserPassword,UserName,ID) values('"+password+"','"+user+"','"+id+"');");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}
	
	public void addDislikedMovie(String password, String user, String id) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into DislikedMovie(UserPassword,UserName,ID) values('"+password+"','"+user+"','"+id+"');");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }
    
    public void addLikedPerson(String password, String user, String id) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into LikedPeople(UserPassword,UserName,ActorID) values('"+password+"','"+user+"','"+id+"');");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }

    public void addFavMovie(String password, String user, String id) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into FavoriteMovie(UserPassword,UserName,ID) values('"+password+"','"+user+"','"+id+"');");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    }

    public void addFavPerson(String password, String user, String id) {
    	try {
    		statement = connection.createStatement();
    		statement.executeUpdate("insert into FavoritePerson(UserPassword,UserName,ActorID) values('"+password+"','"+user+"','"+id+"');");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}
	
	public void changeUsername(String newuser, String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("update ProgramUser set UserName='"+newuser+"' where UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	public void changePassword(String newpass, String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("update ProgramUser set UserPassword='"+newpass+"' where UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	public void changeAge(int newage, String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("update ProgramUser set age="+newage+" where UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	public void changeName(String newname, String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("update ProgramUser set RealName='"+newname+"' where UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	public int getAgeIndex(int age) {
		if(age<18) return 0;
		if(age<30) return 1;
		if(age<45) return 2;
		return 3;
	}

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

	public void removeLikedMovie(String movieID, String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("delete from LikedMovie where ID='"+movieID+"' and UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	public void removeLikedPerson(String actorID, String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("delete from LikedPeople where actorID='"+actorID+"' and UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	public void removeFavMovie(String movieID, String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("delete from FavoriteMovie where ID='"+movieID+"' and UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

	public void removeFavPerson(String actorID, String user, String password) {
		try {
    		statement = connection.createStatement();
    		statement.executeUpdate("delete from FavoritePerson where actorID='"+actorID+"' and UserName='"+user+"' and UserPassword='"+password+"';");
    		
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
	}

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
    
    public static void main(String args[]) {

    	TestJDBC demoObj = new TestJDBC();
		demoObj.Connection();
		System.out.println(demoObj.simGenreMovie("Enter Password Here","frankie5",1));
		//demoObj.simActorMovie("Enter Password Here","billybob");
    	//String sqlQuery ="select * from student where level = 'JR';";
    	//demoObj.simpleQuery(sqlQuery);
    	//System.out.println(demoObj.verifyLogin("12345", "test1"));
    	//demoObj.addNewUser("user", "password", "name",20);
    	//476 login
    }
    
}
