import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
/*
 * 
 * 
 * 
 * We could not put the each frame in a different classes because it is possible to go back and forth
 * between frames and therefore would create a sort of loop
 * so for organization purposes, this will be almost like a table of contents
 * 
 * All the methods apart from paint follow the same basic layout
 * 
 * Defining all the objects in this order:
 * Labels, buttons, Texts Areas, Data, Lists, and finally pains
 * Then We set the location and text of all the objects in this order:
 * Labels, buttons, Texts Areas, Data, Lists, and finally pains
 * Then we have all the actions that can be done in this order
 * Button click then panel clicks
 * Then we have addition user methods
 * 
 ************ Start The Table of Contents ***************
 *	Paint
 *
 *	newuser
 *
 *	loginscreen
 *
 *	loginhome
 *
 *	selectedMovie
 *
 * 	UserInfo
 * 
 * 	Map
 *
 * 
 * 
 */
public class Drawing extends Canvas {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8464146386181518056L;
	static int width= 2000;
	static int height = 1000;
	static int framecount=0;
	static boolean favoritmoviefound = false;
	static boolean favoritactorfound = false;
	static boolean usercreated = false;
	private static boolean settingchange = false;
	private static JDBC Database = new JDBC();
	private static ArrayList<Pair> searchMovies = new ArrayList<Pair>();
	private static ArrayList<Pair> searchPeople = new ArrayList<Pair>();
	private static ArrayList<Double> longcord = new ArrayList<Double>();
	private static ArrayList<Double> latcord = new ArrayList<Double>();
	private static ArrayList<Object> movieInfo = new ArrayList<Object>();
	private static String currUser;
	private static String currPassword;
	
	// now for the things on the list
	
	static Color purple = new Color(128, 0, 128);
	static Color black = new Color(0, 0, 0);
	static Color white = new Color(255, 255, 255);
	static Color yellow = new Color(255, 255, 0);
	static Color teal = new Color(0,255,255);
	static Color test = new Color(255,0,255);
	static Color grey = new Color(100,100,100);
	static int circlecount = width/6;
	
	public static void main(String[] args) {
		JFrame frameOne = new JFrame();
		Database.Connection();
		loginscreen(frameOne);
		
        
    }
	// the paint methode will paint most of the back groud shapes
	// and be a main driver of the map function
	 @Override
    public void paint(Graphics g) {
		 Random rand = new Random();
		 // this is the methode that creates random dots in the back ground of all sceans
		 // except for the map
       if(framecount !=2) {
           for(int i=0; i< circlecount;i++) {
        	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
        	   g.fillOval(rand.nextInt(width), rand.nextInt(height), rand.nextInt(100), rand.nextInt(100));
           }
       }
       // this is the log in screen
       if(framecount==0) {
    	   g.setColor(black);	
           g.fillOval(width/2-400, height/2-200, 800, 400);
       }
       // this is the new user screen 
       else if(framecount == 1) {
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(0, 150, width/5-20, height-300);
    	   g.fillOval(2*width/5-32, 150, width/5-20, height-300);
    	   g.fillOval(4*width/5-70, 150, width/5-20, height-300);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(1*width/5-17, 150, width/5-20, height-300);
    	   g.fillOval(3*width/5-50, 150, width/5-20, height-300);
       }
       else if(framecount==2) {//For the mapFrame
    	   Toolkit t=Toolkit.getDefaultToolkit(); 
    	   Image i=t.getImage("code\\src\\map2.png");
    	   g.drawImage(i, 0, 0, width, height, this);
    	   for(int j=0; j<longcord.size();j++) {
    		   g.setColor(Color.CYAN);
    		   if(j<10) {
    			   g.fillOval((int) Math.round(longcord.get(j)), (int) Math.round(latcord.get(j)), 50/(j+1), 50/(j+1));
    		   }else {
    			   g.fillOval((int) Math.round(longcord.get(j)), (int) Math.round(latcord.get(j)), 10, 10);
    		   }
    		  
    	   }
    	 
       }else if(framecount==3) {//login user frame where it segests the movies
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(0, 150, width/5-20, height-300);
    	   g.fillOval(2*width/5-32, 150, width/5-20, height-300);
    	   g.fillOval(4*width/5-70, 150, width/5-20, height-300);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(1*width/5-17, 150, width/5-20, height-300);
    	   g.fillOval(3*width/5-50, 150, width/5-20, height-300);
       }else if(framecount==4) {//For the movie specifics (show all tuple info)
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(0, 150, width/5-20, height-300);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(1*width/5-17, 150, width/5-20, height-300);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(2*width/5-32, 150, width/10-20, width/10-20);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(2*width/5-32,height/2-150, width/10-20, height-320-width/10);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(2*width/5-32+width/10, 150, width/10-20, width/10-20);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(2*width/5-32+width/10, 130+width/10, width/10-20, width/10-20);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(2*width/5-32+width/10, 110+width/5, width/10-20, width/10-20);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(2*width/5-32+width/10, 3*width/10+90, width/10-20, width/10-20);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(23*width/40, 150, width/10-20, width/10-20);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(23*width/40, 130+width/10, width/10-20, width/10-20);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(23*width/40, 110+width/5, width/10-20, width/10-20);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(23*width/40, 3*width/10+90, width/10-20, width/10-20);

       }else if(framecount == 5){//User Settings page

    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(2*width/5-32, 150, width/5-20, height-300);
    	   g.setColor(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
    	   g.fillOval(1*width/5-17, 150, width/5-20, height-300);
    	   g.fillOval(3*width/5-50, 150, width/5-20, height-300);
       }
      
    }
	 
	 /*
	  * This is the frame that will be created when the new user
	  * wants to create a new profile
	  */
	 public static void newuser(JFrame inputframe) {
		 Random rand = new Random();
		 inputframe.setVisible(false);
		 inputframe.getContentPane().setBackground(purple);
		 inputframe.setDefaultCloseOperation(inputframe.EXIT_ON_CLOSE);
		 inputframe.setTitle("New User screen");
		 
		 framecount=1;
	     Canvas canvas = new Drawing();
	     canvas.setSize(width, height);
	     // all the labels
		 JLabel UserNameLb = new JLabel();
		 JLabel PasswordLb = new JLabel();
		 JLabel UserrealNameLb = new JLabel();
		 JLabel UserAgeLb = new JLabel();
		 JLabel Newuserinfor = new JLabel();
		 JLabel likedMovielb = new JLabel();
		 JLabel favMovielb = new JLabel();
		 JLabel likedactorslb = new JLabel();
		 JLabel favactorslb = new JLabel();
		 JLabel searchedformovieslb = new JLabel();
		 JLabel likedmoviedlb = new JLabel();
		 JLabel likedmovielb2 = new JLabel();
		 JLabel favoritmovelb = new JLabel();
		 JLabel searchedforactorslb = new JLabel();
		 JLabel likedactorslb1 = new JLabel();
		 JLabel likedactorlbtwo = new JLabel();
		 JLabel favoreteactorlb = new JLabel();
		 
		 // all the buttons
		 JButton submitbtn = new JButton();
		 JButton backbtn = new JButton();
		 JButton instructionsbt1 = new JButton();
		 JButton instructionsbt2 = new JButton();
		 JButton instructionsbt3 = new JButton();
		 JButton instructionsbt4 = new JButton();
		 JButton instructionsbt5 = new JButton();
		 JButton searchmoviebtn = new JButton();
		 JButton searchactorbtn = new JButton();
		 
		 // all the text fields
		 JTextField UserNamefld = new JTextField(100);
		 JTextField Passwordfld = new JTextField(100);
		 JTextField UserRealNamefld = new JTextField(100);
		 JTextField UserAgefld = new JTextField(100);
		 JTextField Searchmoviefld = new JTextField(100);
		 JTextField Searchactorfld = new JTextField(100);
		 
		 // All the vector for the panels
		 Vector dataliked = new Vector();
		 Vector dataliked2 = new Vector();
		 Vector datafavoritMovie = new Vector();
		 Vector data = new Vector();
		 Vector dataserchedactors = new Vector();
		 Vector datalikedactorsone = new Vector();
		 Vector datalikedactorstwo = new Vector();
		 Vector datafavoritactors = new Vector();
		 
		 // All the list for the panels
		 JList  listliked = new JList();
	     JList  listliked2 = new JList();
	     JList  listfavoritmovie = new JList();
	     JList  list = new JList();
	     JList listsearchedactors = new JList();
		 JList listlikeactorsone = new JList();
		 JList listlikeactorstwo = new JList();
		 JList listfavoritactors = new JList();
	     
		 //All The Panes
	     JScrollPane serchedmovies = new JScrollPane(list);
	     JScrollPane likedmovies = new JScrollPane(listliked);
	     JScrollPane likedmovies2 = new JScrollPane(listliked2);
	     JScrollPane favoritmovies = new JScrollPane(listfavoritmovie);
	     JScrollPane searchedactors = new JScrollPane(listsearchedactors);
	     JScrollPane likedactorsone = new JScrollPane(listlikeactorsone);
	     JScrollPane likedactorstwo = new JScrollPane(listlikeactorstwo);
	     JScrollPane favoritactors = new JScrollPane(listfavoritactors);
	    
	     // we will start by just defining the postion and text of all the 
	     // objects added to this frame (new User)
	     // the order they will be defined in is the same order listed above
	     // for all objects
	     
	     //Begin with all lables on this frame
	     UserNameLb.setForeground(white);
	     UserNameLb.setOpaque(true);
	     UserNameLb.setBackground(grey);
	     UserNameLb.setText("User Name: ");
	     UserNameLb.setBounds(35, height/2-160, 150, 50);
	     UserNameLb.setFont(new Font("Courier", Font.BOLD,15));
	     
	     PasswordLb.setForeground(white);
	     PasswordLb.setOpaque(true);
	     PasswordLb.setBackground(grey);
	     PasswordLb.setText("User Password: ");
	     PasswordLb.setBounds(35, height/2-60, 150, 50);
	     PasswordLb.setFont(new Font("Courier", Font.BOLD,15));
	     
	     UserrealNameLb.setForeground(white);
	     UserrealNameLb.setOpaque(true);
	     UserrealNameLb.setBackground(grey);
	     UserrealNameLb.setText("User Real Name: ");
	     UserrealNameLb.setBounds(35, height/2+40, 150, 50);
	     UserrealNameLb.setFont(new Font("Courier", Font.BOLD,15));
	        
	     Newuserinfor.setForeground(white);
	     Newuserinfor.setOpaque(true);
	     Newuserinfor.setBackground(grey);
	     Newuserinfor.setText("Enter In New User INFO");
	     Newuserinfor.setBounds(90, 230, 200, 50);
	     Newuserinfor.setFont(new Font("Courier", Font.BOLD,15));
	     
	     UserAgeLb.setForeground(white);
	     UserAgeLb.setOpaque(true);
	     UserAgeLb.setBackground(grey);
	     UserAgeLb.setText("User Age: ");
	     UserAgeLb.setBounds(35, height/2+140, 150, 50);
	     UserAgeLb.setFont(new Font("Courier", Font.BOLD,15));
		 
	     likedMovielb.setForeground(white);
	     likedMovielb.setOpaque(true);
	     likedMovielb.setBackground(grey);
	     likedMovielb.setText("    Liked Movies");
	     likedMovielb.setBounds(width/5+75, 230, 200, 50);
	     likedMovielb.setFont(new Font("Courier", Font.BOLD,15));
	     
	     favMovielb.setForeground(white);
	     favMovielb.setOpaque(true);
	     favMovielb.setBackground(grey);
	     favMovielb.setText("   Favorite Movies");
	     favMovielb.setBounds(2*width/5+60, 230, 200, 50);
	     favMovielb.setFont(new Font("Courier", Font.BOLD,15));
	     
	     likedactorslb.setForeground(white);
	     likedactorslb.setOpaque(true);
	     likedactorslb.setBackground(grey);
	     likedactorslb.setText("    Liked People");
	     likedactorslb.setBounds(3*width/5+40, 230, 200, 50);
	     likedactorslb.setFont(new Font("Courier", Font.BOLD,15));
		 
	     favactorslb.setForeground(white);
	     favactorslb.setOpaque(true);
	     favactorslb.setBackground(grey);
	     favactorslb.setText("     Favorite Person");
	     favactorslb.setBounds(4*width/5+20, 230, 200, 50);
	     favactorslb.setFont(new Font("Courier", Font.BOLD,15));
	     
	     searchedformovieslb.setForeground(white);
	     searchedformovieslb.setOpaque(true);
	     searchedformovieslb.setBackground(grey);
	     searchedformovieslb.setText("Search results:");
	     searchedformovieslb.setBounds(width/5, height/2-100, 150, 40);
	     searchedformovieslb.setFont(new Font("Courier", Font.BOLD,12));
	     
	     likedmoviedlb.setForeground(white);
	     likedmoviedlb.setOpaque(true);
	     likedmoviedlb.setBackground(grey);
	     likedmoviedlb.setText("Liked Movies:");
	     likedmoviedlb.setBounds(190+width/5, height/2-100, 150, 40);
	     likedmoviedlb.setFont(new Font("Courier", Font.BOLD,12));
	     
	     likedmovielb2.setForeground(white);
	     likedmovielb2.setOpaque(true);
	     likedmovielb2.setBackground(grey);
	     likedmovielb2.setText("Liked Movies: ");
	     likedmovielb2.setBounds(2*width/5-20, height/2-100, 150, 40);
	     likedmovielb2.setFont(new Font("Courier", Font.BOLD,12));
	     
	     favoritmovelb.setForeground(white);
	     favoritmovelb.setOpaque(true);
	     favoritmovelb.setBackground(grey);
	     favoritmovelb.setText("Favorite Movie:");
	     favoritmovelb.setBounds(185+2*width/5, height/2-100, 150, 40);
	     favoritmovelb.setFont(new Font("Courier", Font.BOLD,12));
	     
	     searchedforactorslb.setForeground(white);
	     searchedforactorslb.setOpaque(true);
	     searchedforactorslb.setBackground(grey);
	     searchedforactorslb.setText("Search results:");
	     searchedforactorslb.setBounds(3*width/5-30, height/2-100, 150, 40);
	     searchedforactorslb.setFont(new Font("Courier", Font.BOLD,12));
	        
	     likedactorslb1.setForeground(white);
	     likedactorslb1.setOpaque(true);
	     likedactorslb1.setBackground(grey);
	     likedactorslb1.setText("Liked People:");
	     likedactorslb1.setBounds(167+3*width/5, height/2-100, 150, 40);
	     likedactorslb1.setFont(new Font("Courier", Font.BOLD,12));
	        
	     likedactorlbtwo.setForeground(white);
	     likedactorlbtwo.setOpaque(true);
	     likedactorlbtwo.setBackground(grey);
	     likedactorlbtwo.setText("Liked People: ");
	     likedactorlbtwo.setBounds(4*width/5-60, height/2-100, 150, 40);
	     likedactorlbtwo.setFont(new Font("Courier", Font.BOLD,12));
	        
	     favoreteactorlb.setForeground(white);
	     favoreteactorlb.setOpaque(true);
	     favoreteactorlb.setBackground(grey);
	     favoreteactorlb.setText("Favorite Person:");
	     favoreteactorlb.setBounds(5*width/5-250, height/2-100, 150, 40);
	     favoreteactorlb.setFont(new Font("Courier", Font.BOLD,12));
		 // end of Lables
	     
	     // Start of defining Buttons
		 
	     submitbtn.setText("Submit");
	     submitbtn.setBounds(width/2-180, height-150, 300, 100);
	     submitbtn.setBackground(yellow);
	     submitbtn.setFont(new Font("Courier", Font.BOLD,45));
	        
	     backbtn.setText("Back");
	     backbtn.setBounds(30, 30, 150, 100);
	     backbtn.setBackground(yellow);
	     backbtn.setFont(new Font("Courier", Font.BOLD,45));
		 
	     instructionsbt1.setText("Instructions");
	     instructionsbt1.setBounds(90, 750, 200, 50);
	     instructionsbt1.setBackground(yellow);
	     instructionsbt1.setFont(new Font("Courier", Font.BOLD,12));
			
	     instructionsbt2.setText("Instructions");
	     instructionsbt2.setBounds(width/5+75, 750, 200, 50);
	     instructionsbt2.setBackground(yellow);
	     instructionsbt2.setFont(new Font("Courier", Font.BOLD,15));
			
	     instructionsbt3.setText("Instructions");
	     instructionsbt3.setBounds(2*width/5+60, 750, 200, 50);
	     instructionsbt3.setBackground(yellow);
	     instructionsbt3.setFont(new Font("Courier", Font.BOLD,15));
			
	     instructionsbt4.setText("Instructions");
	     instructionsbt4.setBounds(3*width/5+40, 750, 200, 50);
	     instructionsbt4.setBackground(yellow);
	     instructionsbt4.setFont(new Font("Courier", Font.BOLD,15));
			
	     instructionsbt5.setText("Instructions");
	     instructionsbt5.setBounds(4*width/5+20, 750, 200, 50);
	     instructionsbt5.setBackground(yellow);
	     instructionsbt5.setFont(new Font("Courier", Font.BOLD,15));
	     
	     searchmoviebtn.setText("Search Movie");
	     searchmoviebtn.setBounds(190+width/5,height/2-150, 150, 50);
	     searchmoviebtn.setBackground(yellow);
	     
	     searchactorbtn.setText("Search Person");
	     searchactorbtn.setBounds(167+3*width/5,height/2-150, 150, 50);
	     searchactorbtn.setBackground(yellow);
	     
	     // end of buttons
	     
	     // start of JTextField
	     UserNamefld.setBounds(185, height/2-160, 150,50 );
	     UserNamefld.setBackground(teal);
	     UserNamefld.setText("Enter User Name Here");
	        
	     Passwordfld.setBounds(185, height/2-60, 150,50 );
	     Passwordfld.setBackground(teal);
	     Passwordfld.setText("Enter Password Here");
	        
	     UserRealNamefld.setBounds(185, height/2+40, 150,50 );
	     UserRealNamefld.setBackground(teal);
	     UserRealNamefld.setText("Enter Real Name Here");
	        	        
	     UserAgefld.setBounds(185, height/2+140, 150,50 );
	     UserAgefld.setBackground(teal);
	     UserAgefld.setText("Enter Age Here");
	     
	     Searchmoviefld.setBounds(width/5, height/2-150, 150,50 );
	     Searchmoviefld.setBackground(teal);
	     Searchmoviefld.setText("Search Movie name here");
	     
	     Searchactorfld.setBounds(3*width/5-30, height/2-150, 150,50 );
	     Searchactorfld.setBackground(teal);
	     Searchactorfld.setText("Search Person name here");
	     // end of JTextField
	     
	     // start of JScrollPane
	     serchedmovies.setBounds(width/5,height/2-50,150,200);
	     likedmovies.setBounds(190+width/5,height/2-50,150,200);
	     likedmovies2.setBounds(2*width/5-20,height/2-50,150,200);
	     favoritmovies.setBounds(185+2*width/5,height/2-50,150,100);
	     searchedactors.setBounds(3*width/5-30,height/2-50,150,200);
	     likedactorsone.setBounds(167+3*width/5,height/2-50,150,200);
	     likedactorstwo.setBounds(4*width/5-60,height/2-50,150,200);
	     favoritactors.setBounds(5*width/5-250,height/2-50,150,100);
	     // end of JScrollPane
	     
	     // Now this is where we start to define the fucntionallity of the buttons
	     
	     //This is the most important button
	     // it grabs all the value sthat are placed in the fileds and make sure
	     // they are properly formated as well as grabing all the information from 
	     // the liked/favorte actors/movies list
	     // when it succeeds in created a new user it will log the user in
	     submitbtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
	        		String username = UserNamefld.getText();
	        		String Userpassword = Passwordfld.getText();
	        		String UserRealName = UserRealNamefld.getText();
					String Userage = UserAgefld.getText();
					

					if(!Database.verifyNewUser(username)) {
	        			usercreated = false;
	        			
	        			JOptionPane.showMessageDialog(null, "I am sorry, The User Name "+username+""
	    						+ " Has already Been taken, please choose anouther ");
	        			return;
	        		}
	        		
	        		try {
	        			int age = Integer.parseInt(Userage);
	        			
	        			Database.addNewUser(username, Userpassword,UserRealName,age);
	        			// this is just so it has time to interface with the data base,
	        			// after all creating a new user may take a while
	        			Thread.sleep(2000);
	        			//@ ask allen if this can be removed
	        			
	        			
	        			for(int i = 0; i < datalikedactorsone.size();i++) {
	        				Database.addLikedPerson(Userpassword, username, ((Pair)datalikedactorsone.get(i)).getKey());
	        			}
	        			
	        			for(int i = 0; i < dataliked.size();i++) {
	        				Database.addLikedMovie(Userpassword, username, ((Pair)dataliked.get(i)).getKey());
	        			}
	        			
	        			for(int i = 0; i < datafavoritactors.size();i++) {
	        				Database.addFavPerson(Userpassword, username, ((Pair)datafavoritactors.get(i)).getKey());
	        			}
	        			
	        			for(int i = 0; i < datafavoritMovie.size();i++) {
	        				Database.addFavMovie(Userpassword, username, ((Pair)datafavoritMovie.get(i)).getKey());
	        			}
	        			usercreated = true;
	        			JOptionPane.showMessageDialog(null, "Successfully Created A New User. "
	        					+ "\nPlease Press Okay. Log In may Take A moment");
	        			currUser = username;
	        			currPassword = Userpassword;
	        			inputframe.remove(instructionsbt1);
		        		inputframe.remove(instructionsbt2);
		        		inputframe.remove(instructionsbt3);
		        		inputframe.remove(instructionsbt4);
		        		inputframe.remove(instructionsbt5);
		        		inputframe.remove(PasswordLb);
		    	        inputframe.remove(Newuserinfor);
		    	        inputframe.remove(UserNameLb);
		    	        inputframe.remove(likedMovielb);
		    	        inputframe.remove(favMovielb);
		    	        inputframe.remove(favMovielb);
		    	        inputframe.remove(likedactorslb);
		    	        inputframe.remove(favactorslb);
		    	        inputframe.remove(Passwordfld);
		    	        inputframe.remove(UserNamefld);
		    	        inputframe.remove(likedmovies);
		    	        inputframe.remove(serchedmovies);
		    	        inputframe.remove(Searchmoviefld);
		    	        inputframe.remove(searchmoviebtn);
		    	        inputframe.remove(searchedformovieslb);
		    	        inputframe.remove(likedmoviedlb);
		    	        inputframe.remove(likedmovielb2);
		    	        inputframe.remove(favoritmovelb);
		    	        inputframe.remove(likedmovies2);
		    	        inputframe.remove(favoritmovies);
		    	        inputframe.remove(searchedforactorslb);
		    	        inputframe.remove(likedactorslb1);
		    	        inputframe.remove(searchactorbtn);
		    	        inputframe.remove(Searchactorfld);
		    	        inputframe.remove(likedactorsone);
		    	        inputframe.remove(likedactorstwo);
		    	        inputframe.remove(searchedactors);
		    	        inputframe.remove(favoritactors);
		    	        inputframe.remove(likedactorlbtwo);
		    	        inputframe.remove(favoreteactorlb);
		    	        inputframe.remove(UserrealNameLb);
		    	        inputframe.remove(UserAgeLb);
		    	        inputframe.remove(UserRealNamefld);
		    	        inputframe.remove(UserAgefld);
		    	        inputframe.remove(submitbtn);
		    	        inputframe.remove(backbtn);
		    	        inputframe.remove(canvas);
		    	        loginhome(inputframe);
	        			
	        		} catch (NumberFormatException ne) {
	        			JOptionPane.showMessageDialog(null, "I am sorry, The age "+Userage+""
								+ " is not an Integer, please try again ");
	        			usercreated = false;
	        			return;
	        		} catch (Exception e1) {
	        			usercreated = false;
	        			return;
	        		}
	        	
	            }  
	        });   
		 	
	       
	       // when the back button is clicked
	       // it just clears every thing from the frame and goes back 
	       // to the log in screen
	        backbtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
	        		
	        		inputframe.remove(instructionsbt1);
	        		inputframe.remove(instructionsbt2);
	        		inputframe.remove(instructionsbt3);
	        		inputframe.remove(instructionsbt4);
	        		inputframe.remove(instructionsbt5);
	        		inputframe.remove(PasswordLb);
	    	        inputframe.remove(Newuserinfor);
	    	        inputframe.remove(UserNameLb);
	    	        inputframe.remove(likedMovielb);
	    	        inputframe.remove(favMovielb);
	    	        inputframe.remove(favMovielb);
	    	        inputframe.remove(likedactorslb);
	    	        inputframe.remove(favactorslb);
	    	        inputframe.remove(Passwordfld);
	    	        inputframe.remove(UserNamefld);
	    	        inputframe.remove(likedmovies);
	    	        inputframe.remove(serchedmovies);
	    	        inputframe.remove(Searchmoviefld);
	    	        inputframe.remove(searchmoviebtn);
	    	        inputframe.remove(searchedformovieslb);
	    	        inputframe.remove(likedmoviedlb);
	    	        inputframe.remove(likedmovielb2);
	    	        inputframe.remove(favoritmovelb);
	    	        inputframe.remove(likedmovies2);
	    	        inputframe.remove(favoritmovies);
	    	        inputframe.remove(searchedforactorslb);
	    	        inputframe.remove(likedactorslb1);
	    	        inputframe.remove(searchactorbtn);
	    	        inputframe.remove(Searchactorfld);
	    	        inputframe.remove(likedactorsone);
	    	        inputframe.remove(likedactorstwo);
	    	        inputframe.remove(searchedactors);
	    	        inputframe.remove(favoritactors);
	    	        inputframe.remove(likedactorlbtwo);
	    	        inputframe.remove(favoreteactorlb);
	    	        inputframe.remove(UserrealNameLb);
	    	        inputframe.remove(UserAgeLb);
	    	        inputframe.remove(UserRealNamefld);
	    	        inputframe.remove(UserAgefld);
	    	        inputframe.remove(submitbtn);
	    	        inputframe.remove(backbtn);
	    	       
	    	        inputframe.remove(canvas);
	    	        loginscreen(inputframe);
	            }  
	        });  
	        
	        // this is just a mass instructions
	        // one for each of the ovals 
	        // very simple, when the button is clicked it displays
	        // instructions on a JPane
	        
	        instructionsbt1.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){ 
					 
					 JOptionPane.showMessageDialog(null, "Type In Username, Password, Real Name, and Age in the corresponding boxes on the right\n"
					 		+ "Note: The age must be an Integer\n"
					 		+ "Once all ovals are filled out click Submitt","Instructions", JOptionPane.INFORMATION_MESSAGE);
					
				 }
			 }); 
	        instructionsbt2.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){ 
					 
					 JOptionPane.showMessageDialog(null, "TO SEARCH FOR A MOVIE YOU LIKE\n"
					 		+ "1)Type the name of a movie in the search bar on the left\n"
					 		+ "2) Click the Search button\n"
					 		+ "3) The result should appear on the list on the left. If it does not, try searching for single words in the movie\n\n"
					 		+ "TO ADD MOVIE TO LIKED LIST\n"
					 		+ "1) Click on the movie in your Searched list(left)\n"
					 		+ "2) A popup will appear to confirm that you wish to add the movie\n\n"
					 		+ "TO REMOVE A MOVIE FROM YOUR LIKED LIST\n"
					 		+ "1) Click on the movie in your liked list(right)\n"
					 		+ "2)A popup will appear to confirm that you wish to remove the movie\\n" ,"Instructions", JOptionPane.INFORMATION_MESSAGE);
					
				 }
			 }); 
	        instructionsbt3.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){ 
					 
					 JOptionPane.showMessageDialog(null, "TO ADD A MOVIE TO FAVORITE\n"
					 		+ "1) Click on the movie in your liked list(left)\n"
					 		+ "2) A popup will appear to confirm that you wish to add the movie to favorite\n"
					 		+ "3) Note: you are only allowed one favorite\n\n"
					 		+ "TO REMOVE A MOVIE FROM YOUR FAVORITE LIST \n"
					 		+ "1) Click on the movie in your favorite list(right)\n"
					 		+ "2)A popup will appear to confirm that you wish to remove the movie\n" ,"Instructions", JOptionPane.INFORMATION_MESSAGE);
					
				 }
			 }); 
	        instructionsbt4.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){ 
					 
					 JOptionPane.showMessageDialog(null, "TO SEARCH FOR PERSON YOU LIKE\n"
					 		+ "1)Type the name of an actor/director in the search bar on the left\n"
					 		+ "2) Click the Search button\n"
					 		+ "3) The result should appear on the list on the left. If it does not, try searching for single words in the actor's name\n\n"
					 		+ "TO ADD A PERSON TO LIKED LIST\n"
					 		+ "1) Click on the actor in your Searched list(left)\n"
					 		+ "2) A popup will appear to confirm that you wish to add the actor\n\n"
					 		+ "TO REMOVE A PERSON FROM YOUR LIKED LIST\n"
					 		+ "1) Click on the person in your liked list(right)\n"
					 		+ "2)A popup will appear to confirm that you wish to remove the actor/director\\n" ,"Instructions", JOptionPane.INFORMATION_MESSAGE);
					
				 }
			 }); 
	        instructionsbt5.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){ 
					 
					 JOptionPane.showMessageDialog(null, "TO ADD A PERSON TO FAVORITE\n"
					 		+ "1) Click on the person in your liked list(left)\n"
					 		+ "2) A popup will appear to confirm that you wish to add the person to favorite\n"
					 		+ "3) Note: you are only allowed one favorite\n\n"
					 		+ "TO REMOVE A PERSON FROM YOUR FAVORITE LIST \n"
					 		+ "1) Click on the actor/director in your favorite list(right)\n"
					 		+ "2)A popup will appear to confirm that you wish to remove the actor/director\n" ,"Instructions", JOptionPane.INFORMATION_MESSAGE);
					
				 }
			 }); 
	        // when this button is pushed it gets the text information that the user
	        // typed into the search movie feilds
	        // and returnes anything that resembles that from the movie database
	        searchmoviebtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
					String MovieSearchKey = Searchmoviefld.getText();
					searchMovies = Database.searchMovie(MovieSearchKey);
					// to make sure the data is empty from other searches
					data.clear();
	        		for(int i=0;i<searchMovies.size();i++) {
	        			data.addElement(searchMovies.get(i));
	        		}
	    	        list.setListData(data);
					
	            }  
	        }); 
	        //this works the same as the button above exscept this 
	        // gets the users information from the search actor
	        // and returns a list that resembles what was searched
	        searchactorbtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
					String ActorSearchKey = Searchactorfld.getText();

					searchPeople = Database.searchPeople(ActorSearchKey);
					// clear to make sure no remnants from previos searchs 
	        		dataserchedactors.clear();
	        		for(int i = 0;i<searchPeople.size();i++) {
	        			dataserchedactors.addElement(searchPeople.get(i));
	        		}
	        		listsearchedactors.setListData(dataserchedactors);
	            }  
	        });  
	      
	        // this is now the end of all the button actions
	        
	        // Start of the list interactions
	     
	        list.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     
	                     int res = JOptionPane.showConfirmDialog(null, "Would you "
	                     		+ "like to add "+item.toString()+" to your liked movies list?");
	                     if(res==0) {
	                    	 listliked.setSelectedIndex(0);
	                    	 dataliked.addElement(item);
	                    	 dataliked2.addElement(item);
	                    	 listliked.setListData(dataliked);
	                    	 listliked2.setListData(dataliked2);
	                    	
	                    	
	                     }
	                  }
	               }
	            }
	         });
	        
	        
	        listliked.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you "
	                     		+ "like to remove "+item.toString()+" from your liked list?");
	                     if(res==0) {
	                    	 listliked.setSelectedIndex(0);
	                    	 dataliked.remove(item);
	                    	 dataliked2.remove(item);
	                    	 listliked.setListData(dataliked);
	                    	 listliked2.setListData(dataliked2);
	                    	
	                     };
	                  }
	               }
	            }
	         });
	        
	        listliked2.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     if(!favoritmoviefound) {
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like"
	                     		+ " to add "+item.toString()+" as your favorite Movie? ");
	                     if(res==0) {
	                    	 datafavoritMovie.add(item);
	                    	 listfavoritmovie.setListData(datafavoritMovie);
	                    	 // this just makes sure you can only have one favorite
	                    	 // we check here so we do not have to check in the data bace
	                    	 favoritmoviefound = true;
	                     }
	                  }
	                  }
	               }
	            }
	         });
	        listfavoritmovie.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like"
	                     		+ " to remove "+item.toString()+" from your favorite movie");
	                     if(res==0) {
	                    	 listliked.setSelectedIndex(0);
	                    	 datafavoritMovie.remove(item);
	                    	 listfavoritmovie.setListData(datafavoritMovie);
	                    	 favoritmoviefound = false;
	                    	
	                     };
	                  }
	               }
	            }
	         });
	        listsearchedactors.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to add "+item.toString()+" to "
	                     		+ "your liked person list?");
	                     if(res==0) {
	                    	 listlikeactorsone.setSelectedIndex(0);
	                    	 datalikedactorsone.addElement(item);
	                    	 datalikedactorstwo.addElement(item);
	                    	 listlikeactorsone.setListData(datalikedactorsone);
	                    	 listlikeactorstwo.setListData(datalikedactorstwo);
	                    	
	                     }
	                  }
	               }
	            }
	         });
	        
	        listlikeactorsone.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to remove "+item.toString()+" "
	                     		+ "from your liked list?");
	                     if(res==0) {
	                    	 listlikeactorsone.setSelectedIndex(0);
	                    	 datalikedactorsone.remove(item);
	                    	 datalikedactorstwo.remove(item);
	                    	 listlikeactorsone.setListData(datalikedactorsone);
	                    	 listlikeactorstwo.setListData(datalikedactorstwo);
	                    	
	                     };
	                  }
	               }
	            }
	         });
		       
	        listlikeactorstwo.addMouseListener(new MouseAdapter() {
		            public void mouseClicked(MouseEvent me) {
		               if (me.getClickCount() == 1) {
		                  JList target = (JList)me.getSource();
		                  int index = target.locationToIndex(me.getPoint());
		                  if (index >= 0) {
		                     Object item = target.getModel().getElementAt(index);
		                     if(!favoritactorfound) {
		                     int res = JOptionPane.showConfirmDialog(null, "Would you like to add "+item.toString()+" "
		                     		+ "as your favorite Person? ");
		                     if(res==0) {
		                    	 datafavoritactors.add(item);
		                    	 listfavoritactors.setListData(datafavoritactors);
		                    	 favoritactorfound = true;
		                    	
		                     }else {
		                    	
		                     }
		                  }
		                  }
		               }
		            }
		         });
	        
	        listfavoritactors.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like"
	                     		+ " to remove "+item.toString()+" from your favorite Person");
	                     if(res==0) {
	                    	 //listliked.setSelectedIndex(0);
	                    	 datafavoritactors.remove(item);
	                    	 listfavoritactors.setListData(datafavoritactors);
	                    	 favoritactorfound = false;
	                    	
	                     };
	                  }
	               }
	            }
	         });

	        // additional code need to help the user
	        
	        // this just makes it so when you click the text box it clears what 
	        // was last in it
	        UserNamefld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		UserNamefld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	});
	        Passwordfld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		Passwordfld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	}); 
	        UserRealNamefld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		UserRealNamefld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	}); 
	        UserAgefld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		UserAgefld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	});  
	        Searchmoviefld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		Searchmoviefld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	}); 
	        Searchactorfld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		Searchactorfld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	});  
	        
	        // make sure to add all the object to the frame and set it to visable
	        
	        inputframe.add(instructionsbt1);
	        inputframe.add(instructionsbt2);
	        inputframe.add(instructionsbt3);
	        inputframe.add(instructionsbt4);
	        inputframe.add(instructionsbt5);
	        inputframe.add(PasswordLb);
	        inputframe.add(Newuserinfor);
	        inputframe.add(UserNameLb);
	        inputframe.add(likedMovielb);
	        inputframe.add(favMovielb);
	        inputframe.add(favMovielb);
	        inputframe.add(likedactorslb);
	        inputframe.add(favactorslb);
	        inputframe.add(Passwordfld);
	        inputframe.add(UserNamefld);
	        inputframe.add(likedmovies);
	        inputframe.add(serchedmovies);
	        inputframe.add(Searchmoviefld);
	        inputframe.add(searchmoviebtn);
	        inputframe.add(searchedformovieslb);
	        inputframe.add(likedmoviedlb);
	        inputframe.add(likedmovielb2);
	        inputframe.add(favoritmovelb);
	        inputframe.add(likedmovies2);
	        inputframe.add(favoritmovies);
	        inputframe.add(searchedforactorslb);
	        inputframe.add(likedactorslb1);
	        inputframe.add(searchactorbtn);
	        inputframe.add(Searchactorfld);
	        inputframe.add(likedactorsone);
	        inputframe.add(likedactorstwo);
	        inputframe.add(searchedactors);
	        inputframe.add(favoritactors);
	        inputframe.add(likedactorlbtwo);
	        inputframe.add(favoreteactorlb);
	        inputframe.add(UserrealNameLb);
	        inputframe.add(UserAgeLb);
	        inputframe.add(UserRealNamefld);
	        inputframe.add(UserAgefld);
	        inputframe.add(submitbtn);
	        inputframe.add(backbtn);
	        inputframe.add(canvas);
	        inputframe.pack();
	        inputframe.setVisible(true);
	        
	      
	 }
	 //This is the log in screen. 
	 // the very first screen you see when starting up the program
	 // from here you can log in or create a new account
	 public static void loginscreen(JFrame inputframe) {
		 	framecount =0;
		 	inputframe.getContentPane().setBackground(purple);
			inputframe.setDefaultCloseOperation(inputframe.EXIT_ON_CLOSE);
			inputframe.setTitle("Log in start screen");
			inputframe.setVisible(true);
	        Canvas canvas = new Drawing();
	        canvas.setSize(width, height);
	        // All the Labels
		 	JLabel Labelone = new JLabel();
			JLabel UserNameLb = new JLabel();
			JLabel PasswordLb = new JLabel();
			
			// All the Buttons 
			JButton newuserbtn = new JButton();
			JButton loginbtn = new JButton();
			
			// All the TextFields
			JTextField fieldone = new JTextField(100);
			JTextField UserNamefld = new JTextField(100);
			JTextField Passwordfld = new JTextField(100);
			
	        // Now we start to place in all the objects
			// to the need postion with the needed text
			
			// the start of the lables
	        Labelone.setText("Log In");
	        Labelone.setBounds(width/2-150, height/2-350, 300, 150);
	        Labelone.setFont(new Font("Courier", Font.BOLD,75));
	        
	        UserNameLb.setForeground(white);
	        UserNameLb.setOpaque(true);
	        UserNameLb.setBackground(black);
	        UserNameLb.setText("User Name: ");
	        UserNameLb.setBounds(width/2-200, height/2-150, 200, 50);
	        UserNameLb.setFont(new Font("Courier", Font.BOLD,20));
	        
	        PasswordLb.setForeground(white);
	        PasswordLb.setOpaque(true);
	        PasswordLb.setBackground(black);
	        PasswordLb.setText("User Password: ");
	        PasswordLb.setBounds(width/2-200, height/2-50, 200, 50);
	        PasswordLb.setFont(new Font("Courier", Font.BOLD,20));
	        
	        // end of the labels
	        
	        // start of the buttons
	        newuserbtn.setText("Dont't Have a Log In?");
	        newuserbtn.setBounds(width/2,height/2+50, 200, 50);
	        newuserbtn.setBackground(yellow);
	        
	        loginbtn.setText("LOG IN");
	        loginbtn.setBounds(width/2-200,height/2+50, 200, 50);
	        loginbtn.setBackground(test);
	        // end of the buttons
	        
	        //start of the Text feilds
	        UserNamefld.setBounds(width/2, height/2-150, 200,50 );
	        UserNamefld.setBackground(teal);
	        UserNamefld.setText("Enter User Name Here");
	        
	        
	        Passwordfld.setBounds(width/2, height/2-50, 200,50 );
	        Passwordfld.setBackground(teal);
	        Passwordfld.setText("Enter Password Here");
	       // end of the Text fields
	        
	        
	        
	        // start of the interaction methods
	        // Buttin interactions
	        
	        //this just removes all the objects and then calls the new user
	        // method to create that frame
	        newuserbtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
	        		inputframe.remove(UserNamefld);
	        		inputframe.remove(Passwordfld);
	        		inputframe.remove(UserNameLb);
	        		inputframe.remove(PasswordLb);
	        		inputframe.remove(loginbtn);
	        		inputframe.remove(newuserbtn);
	        		inputframe.remove(canvas);
	        		newuser(inputframe);
	        	
	            }  
	        });  
	       
	        
	       // this method confirms that the typed in username and password 
	        // are a valid pair inside out databace
	        loginbtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){
	        		String username = UserNamefld.getText();
	        		String password = Passwordfld.getText();
	        		JDBC Database = new JDBC();
	        		Database.Connection();
	        		if(Database.verifyLogin(username,password)) {
	        			currUser = username;
	        			currPassword = password;
	        			inputframe.remove(UserNamefld);
		        		inputframe.remove(Passwordfld);
		        		inputframe.remove(UserNameLb);
		        		inputframe.remove(PasswordLb);
		        		inputframe.remove(loginbtn);
		        		inputframe.remove(newuserbtn);
		        		inputframe.remove(canvas);
		        		loginhome(inputframe);
	        		} else{
					JOptionPane.showMessageDialog(null, "I am sorry, The Username or "
							+ "Password is incorrect, Please try again");
				}	
	            }  
	        }); 
	        
	      // other helpfull aspects for the user
	        
	      // this just makes it so when a user clicks on a text box the text is removed
	      // before the user starts to type  
	        UserNamefld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		UserNamefld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	}); 
	        Passwordfld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		Passwordfld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	}); 
	        
	        
	        // make sure to add all the objects to the frame
	        inputframe.add(UserNamefld);
	        inputframe.add(Passwordfld);
	        inputframe.add(UserNameLb);
	        inputframe.add(PasswordLb);
	        inputframe.add(loginbtn);
	        inputframe.add(newuserbtn);
	        inputframe.add(canvas);
	        inputframe.pack();
	        inputframe.setVisible(true);
	        
	       
	       
		 
	 }
	 //The log in home
	 // the real meat and potatos of the program
	 // this is where the prgrams recomends the movies baced on other liked 
	 // movies and actors 
	 public static void loginhome(JFrame inputframe) {
		 	inputframe.setVisible(false);
		 	framecount =3;
		 	inputframe.getContentPane().setBackground(purple);
			inputframe.setDefaultCloseOperation(inputframe.EXIT_ON_CLOSE);
			inputframe.setTitle("Log in Home");
			inputframe.setVisible(true);
	        Canvas canvas = new Drawing();
	        canvas.setSize(width, height);
	        //all the buttons
	        JButton logoutbtn = new JButton();
			JButton usersettingbtn = new JButton();
	        //all Text Areas
	        JTextArea SimActorlb = new JTextArea();
	        JTextArea SimGenraLb = new JTextArea();
	        JTextArea SimDirectorLb = new JTextArea();
	        JTextArea ComboLb = new JTextArea();
	        JTextArea instrucLb = new JTextArea();
	        JTextArea instruclistLb = new JTextArea();
	        // all the data for the panes
	        Vector datasimactor = new Vector();
			Vector datasimgen = new Vector();
			Vector datasimdir = new Vector();
			Vector datasimall = new Vector();
			// all the lists for the data for the panes
			JList listsimactor = new JList();
			JList listsingen = new JList();
			JList listsindirc = new JList();
			JList listsinall = new JList();
			// all the panes
			JScrollPane simactorpn = new JScrollPane(listsimactor);
		    JScrollPane simgenon = new JScrollPane(listsingen);
		    JScrollPane simdircon= new JScrollPane(listsindirc);
		    JScrollPane simallpn = new JScrollPane(listsinall);
		    
		    // Now we start to place in all the objects
		 	// to the need postion with the needed text
	        
	        // start of the buttons
		    logoutbtn.setText("Log out");
		    logoutbtn.setBounds(20,50, 200, 50);
		    logoutbtn.setBackground(yellow);
	        
		    usersettingbtn.setText("User Settings");
		    usersettingbtn.setBounds(width-300,50, 200, 50);
		    usersettingbtn.setBackground(yellow);
		    // end of the buttons
		    
		    // start of the TextArea's
			SimActorlb.setForeground(white);
			SimActorlb.setOpaque(true);
			SimActorlb.setBackground(grey);
			SimActorlb.setText("Recommended Movies \nWith liked actors");
			SimActorlb.setBounds(width/5+70, 200, 200, 75);
			SimActorlb.setFont(new Font("Courier", Font.BOLD,15));
			
			SimGenraLb.setForeground(white);
			SimGenraLb.setOpaque(true);
			SimGenraLb.setBackground(grey);
			SimGenraLb.setText("Recommended Movies \nWith Genre of\nFavorite Movie");
			SimGenraLb.setBounds(2*width/5+60, 200, 200, 75);
			SimGenraLb.setFont(new Font("Courier", Font.BOLD,15));
			
			SimDirectorLb.setForeground(white);
			SimDirectorLb.setOpaque(true);
			SimDirectorLb.setBackground(grey);
			SimDirectorLb.setText("Recommended Movies \nWith liked Directors");
			SimDirectorLb.setBounds(3*width/5+42, 200, 200, 75);
			SimDirectorLb.setFont(new Font("Courier", Font.BOLD,15));
			
			ComboLb.setForeground(white);
			ComboLb.setOpaque(true);
			ComboLb.setBackground(grey);
			ComboLb.setText("Recommended Movies \nWith Liked Actors\n AND Directors");
			ComboLb.setBounds(4*width/5+22, 200, 200, 75);
			ComboLb.setFont(new Font("Courier", Font.BOLD,15));
			
			instrucLb.setForeground(white);
			instrucLb.setOpaque(true);
			instrucLb.setBackground(grey);
			instrucLb.setText("\n    Instructions");
			instrucLb.setBounds(90, 200, 200, 75);
			instrucLb.setFont(new Font("Courier", Font.BOLD,15));
			
			instruclistLb.setForeground(white);
			instruclistLb.setOpaque(true);
			instruclistLb.setBackground(grey);
			instruclistLb.setText("1) The left consists of movie\nrecommendations"
					+ " organized by three \ncategories and one combination\n"
					+ "2) Click on the list box you wish \nto select more information"
					+ " about \na movie with in that box \n"
					+"3) Select the movie inside \nthe list by clicking on it");
			instruclistLb.setBounds(40, 300, 300, 400);
			instruclistLb.setFont(new Font("Courier", Font.BOLD,15));
			
			// end of the TextArea's
			
			// start of Panes
		    simactorpn.setBounds(width/5+20,height/2-200,300,400);
		    simgenon.setBounds(2*width/5+8,height/2-200,300,400);
		    simdircon.setBounds(3*width/5-10,height/2-200,300,400);
		    simallpn.setBounds(4*width/5-27,height/2-200,300,400);
		    // end of panes
		    
		    // now start the methods that are called when the user interacts with the frame by clicking
		    
		    //  start buttons
		    
		    // removes all the objects and then jumps to the log in screen
		    logoutbtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
	        		inputframe.remove(logoutbtn);
         			inputframe.remove(usersettingbtn);
         		    inputframe.remove(simactorpn);
         			inputframe.remove(simgenon);
         			inputframe.remove(simdircon);
         			inputframe.remove(simallpn);
         			inputframe.remove(instruclistLb);
         			inputframe.remove(instrucLb);
         			inputframe.remove(ComboLb);
         			inputframe.remove(SimDirectorLb);
         			inputframe.remove(SimActorlb);
         			inputframe.remove(SimGenraLb);
         	        inputframe.remove(canvas);
	        		inputframe.remove(canvas);
	        		loginscreen(inputframe);
	        	
	            }  
	        });  
		    // removes all the object and then jumps to the userinfo
		    // method that creates the Userinfro tab 
		    usersettingbtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
	        		inputframe.remove(logoutbtn);
         			inputframe.remove(usersettingbtn);
         		    inputframe.remove(simactorpn);
         			inputframe.remove(simgenon);
         			inputframe.remove(simdircon);
         			inputframe.remove(simallpn);
         			inputframe.remove(instruclistLb);
         			inputframe.remove(instrucLb);
         			inputframe.remove(ComboLb);
         			inputframe.remove(SimDirectorLb);
         			inputframe.remove(SimActorlb);
         			inputframe.remove(SimGenraLb);
         	        inputframe.remove(canvas);
	        		inputframe.remove(canvas);
	        		Userinfor(inputframe);
	        	
	            }  
	        });  
		    // end buttons
		    
		    
		    // this gets all the information from the database and and places the information 
		    //inside the list to be displayed and clicked on
		  //inside the list to be displayed and clicked on
			int age = Integer.parseInt(Database.getAge(currUser,currPassword));
			int ageIndex = Database.getAgeIndex(age);

		    ArrayList<Pair> simactorMovies = Database.simActorMovie(currPassword,currUser,ageIndex);
			ArrayList<Pair> simgenreMovies = Database.simGenreMovie(currPassword,currUser,ageIndex);
			ArrayList<Pair> simdirMovies = Database.simDirMovie(currPassword,currUser,ageIndex);
			ArrayList<Pair> simallMovies = Database.simAllMovie(currPassword,currUser,ageIndex);

		 	datasimactor.clear();
		 	datasimgen.clear();
		 	datasimdir.clear();
		 	datasimall.clear();

			for(int i =0;i<simactorMovies.size();i++) {
				datasimactor.addElement(simactorMovies.get(i));
			}

			for(int i =0;i<simgenreMovies.size();i++) {
				datasimgen.addElement(simgenreMovies.get(i));
			}

			for(int i =0;i<simdirMovies.size();i++) {
				datasimdir.addElement(simdirMovies.get(i));
			}

			for(int i =0;i<simallMovies.size();i++) {
				datasimall.addElement(simallMovies.get(i));
			}

			listsimactor.setListData(datasimactor);
			listsingen.setListData(datasimgen);
			listsindirc.setListData(datasimdir);
			listsinall.setListData(datasimall);
		    //now starting the interaction with the panels
			// the are all functionally the same
			// remove all the objects from the frame and call 
			// selected movie with an input of the movie they
			// clicked on
		    listsimactor.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like"
	                     		+ " to look into the movie "+item.toString()+" more?");
	                     if(res==0) {
	                    	inputframe.remove(logoutbtn);
	             			inputframe.remove(usersettingbtn);
	             		    inputframe.remove(simactorpn);
	             			inputframe.remove(simgenon);
	             			inputframe.remove(simdircon);
	             			inputframe.remove(simallpn);
	             			inputframe.remove(instruclistLb);
	             			inputframe.remove(instrucLb);
	             			inputframe.remove(ComboLb);
	             			inputframe.remove(SimDirectorLb);
	             			inputframe.remove(SimActorlb);
	             			inputframe.remove(SimGenraLb);
	             	        inputframe.remove(canvas);
	             	        selectedMovie(inputframe, item);
	                    	
	                     };
	                  }
	               }
	            }
	         });
		    
		    listsingen.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like"
	                     		+ " to look into the movie "+item.toString()+" more?");
	                     if(res==0) {
	                    	inputframe.remove(logoutbtn);
	             			inputframe.remove(usersettingbtn);
	             		    inputframe.remove(simactorpn);
	             			inputframe.remove(simgenon);
	             			inputframe.remove(simdircon);
	             			inputframe.remove(simallpn);
	             			inputframe.remove(instruclistLb);
	             			inputframe.remove(instrucLb);
	             			inputframe.remove(ComboLb);
	             			inputframe.remove(SimDirectorLb);
	             			inputframe.remove(SimActorlb);
	             			inputframe.remove(SimGenraLb);
	             	        inputframe.remove(canvas);
	             	        selectedMovie(inputframe, item);
	                    	
	                     };
	                  }
	               }
	            }
	         });
		    listsindirc.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like"
	                     		+ " to look into the movie "+item.toString()+" more?");
	                     if(res==0) {
	                    	inputframe.remove(logoutbtn);
	             			inputframe.remove(usersettingbtn);
	             		    inputframe.remove(simactorpn);
	             			inputframe.remove(simgenon);
	             			inputframe.remove(simdircon);
	             			inputframe.remove(simallpn);
	             			inputframe.remove(instruclistLb);
	             			inputframe.remove(instrucLb);
	             			inputframe.remove(ComboLb);
	             			inputframe.remove(SimDirectorLb);
	             			inputframe.remove(SimActorlb);
	             			inputframe.remove(SimGenraLb);
	             	        inputframe.remove(canvas);
	             	        selectedMovie(inputframe, item);
	                    	
	                     };
	                  }
	               }
	            }
	         });
		    listsinall.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like"
	                     		+ " to look into the movie "+item.toString()+" more?");
	                     if(res==0) {
	                    	inputframe.remove(logoutbtn);
	             			inputframe.remove(usersettingbtn);
	             		    inputframe.remove(simactorpn);
	             			inputframe.remove(simgenon);
	             			inputframe.remove(simdircon);
	             			inputframe.remove(simallpn);
	             			inputframe.remove(instruclistLb);
	             			inputframe.remove(instrucLb);
	             			inputframe.remove(ComboLb);
	             			inputframe.remove(SimDirectorLb);
	             			inputframe.remove(SimActorlb);
	             			inputframe.remove(SimGenraLb);
	             	        inputframe.remove(canvas);
	             	        selectedMovie(inputframe, item);
	                    	
	                     };
	                  }
	               }
	            }
	         });
		    
		    
		    // just adds all the object 
		    //to the frame
		    inputframe.add(logoutbtn);
			inputframe.add(usersettingbtn);
		    inputframe.add(simactorpn);
			inputframe.add(simgenon);
			inputframe.add(simdircon);
			inputframe.add(simallpn);
			inputframe.add(instruclistLb);
			inputframe.add(instrucLb);
			inputframe.add(ComboLb);
			inputframe.add(SimDirectorLb);
			inputframe.add(SimActorlb);
			inputframe.add(SimGenraLb);
	        inputframe.add(canvas);  
	        inputframe.pack();
	        inputframe.setVisible(true);
		 
		 
	 }
	 // the selected movie method displays all the attributes about a movie
	 // this is also the location where a user can add liked movie or liked actors 
	 // also the location where the the user can click the map button
	 public static void selectedMovie(JFrame inputframe, Object Movie) {
		 framecount=4;
		 Random rand = new Random();
		 inputframe.getContentPane().setBackground(new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255)));
		 inputframe.setDefaultCloseOperation(inputframe.EXIT_ON_CLOSE);
		 inputframe.setTitle("Selected Movie screen");
		 inputframe.setVisible(true);
	     Canvas canvas = new Drawing();
	     canvas.setSize(width, height);
	     
	     // all the labels
	     JLabel synopsislb = new JLabel();
		 JLabel Listofactorslb = new JLabel();
		 JLabel Dirclb = new JLabel();
		 JLabel Ratinglb = new JLabel();
		 JLabel grossincomelb = new JLabel();
		 JLabel contreylb = new JLabel();
		 JLabel langlb = new JLabel();
		 JLabel realsedatelb = new JLabel();
		 JLabel Durationlb = new JLabel();
		 JLabel MovieGenrelb = new JLabel();
		 JLabel IDlb = new JLabel();
		 JLabel Titlelb = new JLabel();
		 // all the buttons
		 JButton likedbtn = new JButton();
		 JButton notlikedbtn = new JButton();
		 JButton mapbtn = new JButton();
		 JButton backbtn = new JButton();
		 // all TextAreas
		 JTextArea Listofwriterslb = new JTextArea();
		 JTextArea actorinstructlb = new JTextArea();
		 JTextArea Directorinfolb = new JTextArea();
		 JTextArea ratingsinfolb = new JTextArea();
		 JTextArea grossinfolb = new JTextArea();
		 JTextArea contreyinfolb = new JTextArea();
		 JTextArea langinfolb = new JTextArea();
		 JTextArea realsedateinfolb = new JTextArea();
		 JTextArea Durationinfrolb = new JTextArea();
		 JTextArea MovieGenreinfolb = new JTextArea();
		 JTextArea IDinfolb = new JTextArea();
		 JTextArea synopsistxtlb = new JTextArea();
		 // all data for pane
		 Vector dataactor = new Vector();
		 Vector datawrtiers = new Vector();
		 Vector datadirc = new Vector();
		 // all the lists for the pane
		 JList listactor = new JList();
		 JList listwriters = new JList();
		 JList listdirc = new JList();
		 // all the panes
		 JScrollPane actopn = new JScrollPane(listactor);
		 JScrollPane wrtierspn = new JScrollPane(listwriters);
		 JScrollPane dircpn = new JScrollPane(listdirc);
		 
		 
		 // we interact with the data base to get the information
		 // from the specfic movie
		 String IDst = ((Pair)Movie).getKey();
		 movieInfo.clear();
		 movieInfo = Database.getMovieInfo(IDst);
		// create table movie(ID, Title, dateOfRelease, MovieGenre, Duration, country , Writers, worldgross, Director, mLanguage, listofactors
		 String Titlest = movieInfo.get(1).toString();
		 String origSyn = movieInfo.get(2).toString();
		 String Synopsisst = origSyn.replaceAll("(.{1,25})(?:$| )", "$1\n");
		 String dircstring = movieInfo.get(9).toString(); 
		 String Ratingst = movieInfo.get(12).toString();
	     String grossst =  movieInfo.get(8).toString();
	     String contreyst = movieInfo.get(6).toString();
	     String langst =movieInfo.get(10).toString();
	     String realsedatest =movieInfo.get(3).toString();
	     String Durationst =movieInfo.get(5).toString();
		 String MovieGenrest =movieInfo.get(4).toString();
		 String actors = movieInfo.get(11).toString();
		 // we need to split the string of actors so they can be placen 
		 //in the pane so they can be clicked on 
		 
		 ArrayList<Pair> actorsinMovie = Database.getActors(IDst);
		 // @ ask allen if we still need this
		 
		 for(int i = 0; i<actorsinMovie.size();i++) {
			 dataactor.addElement(actorsinMovie.get(i));
		 }
		 listactor.setListData(dataactor);

		 String writers = movieInfo.get(7).toString();
		 writers = writers.replaceAll(",\\s+",",");
		 String[] listofwriters = writers.split(",");

		 for(int i = 0;i<listofwriters.length;i++) {
			 datawrtiers.addElement(listofwriters[i]);
		 }
		 listwriters.setListData(datawrtiers);
		
		 ArrayList<Pair> directorsinMovie = Database.getDirectors(IDst);
		 for(int i = 0; i<directorsinMovie.size();i++) {
			datadirc.addElement(directorsinMovie.get(i));
		}

		 //ArrayList<Pair> actorsinMovie = Database.getPeople(listofactors);
		 // @ ask allen if we still need this
		 


		 listdirc.setListData(datadirc);
		// Now we start to place in all the objects
		// to the need postion with the needed text 
		 
		 // labels
		 synopsislb.setForeground(white);
		 synopsislb.setOpaque(true);
		 synopsislb.setBackground(grey);
		 synopsislb.setText("    Synopsis");
		 synopsislb.setBounds(100, 180, 170, 50);
		 synopsislb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 synopsistxtlb.setForeground(black);
		 synopsistxtlb.setOpaque(true);
		 synopsistxtlb.setBackground(white);
		 synopsistxtlb.setText(Synopsisst);
		 synopsistxtlb.setBounds(70, 250, 230, 500);
		 synopsistxtlb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 Listofactorslb.setForeground(white);
		 Listofactorslb.setOpaque(true);
		 Listofactorslb.setBackground(grey);
		 Listofactorslb.setText("  List Of Actors");
		 Listofactorslb.setBounds(width/5+85, 180, 170, 50);
		 Listofactorslb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 actorinstructlb.setForeground(white);
		 actorinstructlb.setOpaque(true);
		 actorinstructlb.setBackground(grey);
		 actorinstructlb.setText(" See an Actor you\n       Like?\n Click their Name \n Follow The Popup");
		 actorinstructlb.setBounds(width/5+85, 780, 170, 100);
		 actorinstructlb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 Dirclb.setForeground(white);
		 Dirclb.setOpaque(true);
		 Dirclb.setBackground(grey);
		 Dirclb.setText("Director");
		 Dirclb.setBounds(2*width/5+10, 180, 100, 50);
		 Dirclb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 
		 Listofwriterslb.setForeground(white);
		 Listofwriterslb.setOpaque(true);
		 Listofwriterslb.setBackground(grey);
		 Listofwriterslb.setText("List of \nWriters");
		 Listofwriterslb.setBounds(2*width/5+10, 400, 100, 50);
		 Listofwriterslb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 Ratinglb.setForeground(white);
		 Ratinglb.setOpaque(true);
		 Ratinglb.setBackground(grey);
		 Ratinglb.setText("Ratings");
		 Ratinglb.setBounds(2*width/5+210, 180, 100, 50);
		 Ratinglb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 ratingsinfolb.setForeground(black);
		 ratingsinfolb.setOpaque(true);
		 ratingsinfolb.setBackground(white);
		 ratingsinfolb.setText(Ratingst);
		 ratingsinfolb.setBounds(2*width/5+210, 250, 100, 50);
		 
		 grossincomelb.setForeground(white);
		 grossincomelb.setOpaque(true);
		 grossincomelb.setBackground(grey);
		 grossincomelb.setText("Gross Income");
		 grossincomelb.setBounds(2*width/5+190, 350, 150, 50);
		 grossincomelb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 grossinfolb.setForeground(black);
		 grossinfolb.setOpaque(true);
		 grossinfolb.setBackground(white);
		 grossinfolb.setText(grossst);
		 grossinfolb.setBounds(2*width/5+190, 410, 150, 50);
		 
		 contreylb.setForeground(white);
		 contreylb.setOpaque(true);
		 contreylb.setBackground(grey);
		 contreylb.setText("Country Orgin");
		 contreylb.setBounds(2*width/5+190, 520, 150, 50);
		 contreylb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 contreyinfolb.setForeground(black);
		 contreyinfolb.setOpaque(true);
		 contreyinfolb.setBackground(white);
		 contreyinfolb.setText(contreyst);
		 contreyinfolb.setBounds(2*width/5+190, 580, 150, 50);
		 
		 langlb.setForeground(white);
		 langlb.setOpaque(true);
		 langlb.setBackground(grey);
		 langlb.setText("Languge");
		 langlb.setBounds(2*width/5+190, 700, 150, 50);
		 langlb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 langinfolb.setForeground(black);
		 langinfolb.setOpaque(true);
		 langinfolb.setBackground(white);
		 langinfolb.setText(langst);
		 langinfolb.setBounds(2*width/5+190, 760, 150, 50);
		 
		 realsedatelb.setForeground(white);
		 realsedatelb.setOpaque(true);
		 realsedatelb.setBackground(grey);
		 realsedatelb.setText("Release Date");
		 realsedatelb.setBounds(93*width/160, 180, 150, 50);
		 realsedatelb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 realsedateinfolb.setForeground(black);
		 realsedateinfolb.setOpaque(true);
		 realsedateinfolb.setBackground(white);
		 realsedateinfolb.setText(realsedatest);
		 realsedateinfolb.setBounds(93*width/160, 250, 150, 50);
		 
		 Durationlb.setForeground(white);
		 Durationlb.setOpaque(true);
		 Durationlb.setBackground(grey);
		 Durationlb.setText("Duration");
		 Durationlb.setBounds(93*width/160, 350, 150, 50);
		 Durationlb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 Durationinfrolb.setForeground(black);
		 Durationinfrolb.setOpaque(true);
		 Durationinfrolb.setBackground(white);
		 Durationinfrolb.setText(Durationst);
		 Durationinfrolb.setBounds(93*width/160, 410, 150, 50);
		 
		 MovieGenrelb.setForeground(white);
		 MovieGenrelb.setOpaque(true);
		 MovieGenrelb.setBackground(grey);
		 MovieGenrelb.setText("Genre");
		 MovieGenrelb.setBounds(93*width/160, 520, 150, 50);
		 MovieGenrelb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 MovieGenreinfolb.setForeground(black);
		 MovieGenreinfolb.setOpaque(true);
		 MovieGenreinfolb.setBackground(white);
		 MovieGenreinfolb.setText(MovieGenrest);
		 MovieGenreinfolb.setBounds(93*width/160, 580, 150, 50);
		 
		 IDlb.setForeground(white);
		 IDlb.setOpaque(true);
		 IDlb.setBackground(grey);
		 IDlb.setText("Movie ID");
		 IDlb.setBounds(93*width/160, 700, 150, 50);
		 IDlb.setFont(new Font("Courier", Font.BOLD,15));
		 
		 IDinfolb.setForeground(black);
		 IDinfolb.setOpaque(true);
		 IDinfolb.setBackground(white);
		 IDinfolb.setText(IDst);
		 IDinfolb.setBounds(93*width/160, 760, 150, 50);
		 
		 Titlelb.setForeground(white);
		 Titlelb.setText(Titlest);
		 Titlelb.setBounds(200, 50, 1000, 100);
		 Titlelb.setFont(new Font("Courier", Font.BOLD,50));
		 // end labels
		 
		 //start buttons
		 likedbtn.setText("Did you like this Movie?");
		 likedbtn.setBounds(3*width/4, height/4, 200, 100);
		 likedbtn.setBackground(yellow);
		 
		 notlikedbtn.setText("Did you not like this Movie?");
		 notlikedbtn.setBounds(3*width/4, 2*height/4-100, 200, 100);
		 notlikedbtn.setBackground(yellow);
		 
		 mapbtn.setText("You Wanna See A Cool Map?");
		 mapbtn.setBounds(3*width/4, 3*height/4-200, 200, 100);
		 mapbtn.setBackground(yellow);
		 
		 backbtn.setText("Back");
		 backbtn.setBounds(3*width/4, 4*height/4-300, 200, 100);
		 backbtn.setBackground(yellow);
		 // end buttons
		 
		 // start panes 
		 wrtierspn.setBounds(2*width/5+10,460,100,300);
		 actopn.setBounds(width/5+60,250,230,500);
		 dircpn.setBounds(2*width/5+10, 250, 100, 70);
		 // end panes
		 
		 // This section is when action is reqired from a user mouse click
		 
		 // start buttons
		 // the method removes all the object and then calls the map 
		 // function is called to start that frame
		 mapbtn.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 inputframe.remove(dircpn);
				 inputframe.remove(actorinstructlb);
				 inputframe.remove(Titlelb);
				 inputframe.remove(realsedateinfolb);
				 inputframe.remove(Durationinfrolb);
				 inputframe.remove(MovieGenreinfolb);
				 inputframe.remove(IDinfolb);
				 inputframe.remove(grossinfolb);
				 inputframe.remove(contreyinfolb);
				 inputframe.remove(langinfolb);
				 inputframe.remove(ratingsinfolb);
				 inputframe.remove(Directorinfolb);
				 inputframe.remove(wrtierspn);
				 inputframe.remove(actopn);
				 inputframe.remove(synopsistxtlb);
				 inputframe.remove(likedbtn);
				 inputframe.remove(notlikedbtn);
				 inputframe.remove(mapbtn);
				 inputframe.remove(backbtn);
				 inputframe.remove(IDlb);
				 inputframe.remove(MovieGenrelb);
				 inputframe.remove(Durationlb);
				 inputframe.remove(realsedatelb);
				 inputframe.remove(langlb);
				 inputframe.remove(contreylb);
				 inputframe.remove(grossincomelb);
				 inputframe.remove(Ratinglb);
				 inputframe.remove(Listofwriterslb);
				 inputframe.remove(Dirclb);
				 inputframe.remove(Listofactorslb);
				 inputframe.remove(synopsislb);
				 inputframe.remove(canvas);
				 map(inputframe, Movie);
			 }
		 });
		 // the back button removes all the object from this frame
		 // then calls the login home method
		 backbtn.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 //Find Stuff to delete
				 inputframe.remove(dircpn);
				 inputframe.remove(actorinstructlb);
				 inputframe.remove(Titlelb);
				 inputframe.remove(realsedateinfolb);
				 inputframe.remove(Durationinfrolb);
				 inputframe.remove(MovieGenreinfolb);
				 inputframe.remove(IDinfolb);
				 inputframe.remove(grossinfolb);
				 inputframe.remove(contreyinfolb);
				 inputframe.remove(langinfolb);
				 inputframe.remove(ratingsinfolb);
				 inputframe.remove(Directorinfolb);
				 inputframe.remove(wrtierspn);
				 inputframe.remove(actopn);
				 inputframe.remove(synopsistxtlb);
				 inputframe.remove(likedbtn);
				 inputframe.remove(notlikedbtn);
				 inputframe.remove(mapbtn);
				 inputframe.remove(backbtn);
				 inputframe.remove(IDlb);
				 inputframe.remove(MovieGenrelb);
				 inputframe.remove(Durationlb);
				 inputframe.remove(realsedatelb);
				 inputframe.remove(langlb);
				 inputframe.remove(contreylb);
				 inputframe.remove(grossincomelb);
				 inputframe.remove(Ratinglb);
				 inputframe.remove(Listofwriterslb);
				 inputframe.remove(Dirclb);
				 inputframe.remove(Listofactorslb);
				 inputframe.remove(synopsislb);
				 inputframe.remove(canvas);
				 loginhome(inputframe);

			 }
		 });
		 //this adds the current movie to the current user liked list
		 likedbtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
	        		Database.addLikedMovie(currPassword, currUser, IDst);
	        	}  
	        });  
		 // this adds the current movie to to the currents disliked list
		 notlikedbtn.addActionListener(new ActionListener(){  
	        	public void actionPerformed(ActionEvent e){ 
	        		// @ allen when this button the movie that is being refrencs must be added to the list that will never 
	        		// show up on the recomended movies list again
	        		Database.addDislikedMovie(currPassword,currUser,IDst);
	            }  
	        }); 
		 
		 
		 // end buttons
		 
		 //start pane interactions
		 //This is the pane that has the list of actors
		 // when the user clicks one one of the actors names it 
		 // it asked it it would like to add this actor to its liked list
		 listactor.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to add "+item.toString()+" to your liked people List?");
	                     if(res==0) {
							Database.addLikedPerson(currPassword,currUser,((Pair)item).getKey());
	                    	
	                     };
	                  }
	               }
	            }
	         });
		 // this will add the diector also the the list of liked people
		 listdirc.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to add "+item.toString()+" to your liked people List?");
	                     if(res==0) {
							Database.addLikedPerson(currPassword,currUser,((Pair)item).getKey());
	                    	
	                     };
	                  }
	               }
	            }
	         });
		 
		 // then just make sure to add all the objects to the frame 
		 inputframe.add(dircpn);
		 inputframe.add(actorinstructlb);
		 inputframe.add(Titlelb);
		 inputframe.add(realsedateinfolb);
		 inputframe.add(Durationinfrolb);
		 inputframe.add(MovieGenreinfolb);
		 inputframe.add(IDinfolb);
		 inputframe.add(grossinfolb);
		 inputframe.add(contreyinfolb);
		 inputframe.add(langinfolb);
		 inputframe.add(ratingsinfolb);
		 inputframe.add(Directorinfolb);
		 inputframe.add(wrtierspn);
		 inputframe.add(actopn);
		 inputframe.add(synopsistxtlb);
		 inputframe.add(likedbtn);
		 inputframe.add(notlikedbtn);
		 inputframe.add(mapbtn);
		 inputframe.add(backbtn);
		 inputframe.add(IDlb);
		 inputframe.add(MovieGenrelb);
		 inputframe.add(Durationlb);
		 inputframe.add(realsedatelb);
		 inputframe.add(langlb);
		 inputframe.add(contreylb);
		 inputframe.add(grossincomelb);
		 inputframe.add(Ratinglb);
		 inputframe.add(Listofwriterslb);
		 inputframe.add(Dirclb);
		 inputframe.add(Listofactorslb);
		 inputframe.add(synopsislb);
	     inputframe.add(canvas); 
	     inputframe.pack();
	     inputframe.setVisible(true);
	 }
	 // the user information method displays all
	 // the current like and favorited movies and actors
	 // it also allows the user to change the liked movies and actors as well as the
	 // favorite 
	 // it also allows the user to change the username, password, real name, and age
	 // it also allows the user to delete the user account 
	 public static void Userinfor(JFrame inputframe) {
		 framecount=5;
		 inputframe.getContentPane().setBackground(purple);
		 inputframe.setDefaultCloseOperation(inputframe.EXIT_ON_CLOSE);
		 inputframe.setTitle("User Information screen");
		 inputframe.setVisible(true);
	     Canvas canvas = new Drawing();
	     canvas.setSize(width, height);
	     // all the labels
	     JLabel Updateuserinfolb = new JLabel();
	     JLabel UpdateMovieinfolb = new JLabel();
		 JLabel UpdateMoviefavoriteinfolb = new JLabel();
		 JLabel UpdateMovielikeinfolb = new JLabel();
		 JLabel UpdateActorinfolb = new JLabel();
		 JLabel UpdatefavoritActorinfolb = new JLabel();
		 JLabel UpdatelikedActorinfolb = new JLabel();
		 // all the buttons
		 JButton instructionsbt = new JButton();
		 JButton UserNamebtn = new JButton();
		 JButton Passwordbtn = new JButton();
		 JButton UserRealNamebtn = new JButton();
		 JButton UserAgebtn = new JButton();
		 JButton deletebtn = new JButton();
		 JButton backbtn = new JButton();
		 // all the text fields
	     JTextField UserNamefld = new JTextField(100);
		 JTextField Passwordfld = new JTextField(100);
		 JTextField UserRealNamefld = new JTextField(100);
		 JTextField UserAgefld = new JTextField(100);
		 // all the data for the panes
		 Vector datalikedmovies = new Vector();
		 Vector datafavoritemovie = new Vector();
		 Vector datalikedactor = new Vector();
		 Vector datafavoriteactor = new Vector();
		 // all the list for the pane
		 JList listslikedmovies = new JList();
		 JList listsfavoritemovie = new JList();
		 JList listslikedactor = new JList();
		 JList listfavoriteactor = new JList();
		 // all the panes
		 JScrollPane likedmoviepn = new JScrollPane(listslikedmovies);
		 JScrollPane favoritmoviepn = new JScrollPane(listsfavoritemovie);
		 JScrollPane likedactorpn= new JScrollPane(listslikedactor);
		 JScrollPane favoritactorpn = new JScrollPane(listfavoriteactor);
		 
		
		 //this is where we populate the data vectors to like/favorite
		 // movie and actors from the data bace
		 ArrayList<Pair> likedMovies = Database.likedMovie(currPassword,currUser);
		 ArrayList<Pair> likedPeople = Database.likedPeople(currPassword,currUser);
		 ArrayList<Pair> favoriteMovie = Database.favoriteMovie(currPassword,currUser);
		 ArrayList<Pair> favoritePerson = Database.favoritePerson(currPassword,currUser);

		for(int i = 0; i<likedMovies.size();i++) {
			 datalikedmovies.addElement(likedMovies.get(i));
		  }

		for(int i = 0; i<likedPeople.size();i++) {
			datalikedactor.addElement(likedPeople.get(i));
		 }

		for(int i = 0; i<favoriteMovie.size();i++) {
			datafavoritemovie.addElement(favoriteMovie.get(i));
		 }

		for(int i = 0; i<favoritePerson.size();i++) {
			datafavoriteactor.addElement(favoritePerson.get(i));
		 }
		 listslikedmovies.setListData(datalikedmovies);
		 listslikedactor.setListData(datalikedactor);
		 listfavoriteactor.setListData(datafavoriteactor);
		 listsfavoritemovie.setListData(datafavoritemovie);
		 
		 // this is to keep trake on weather or not
		 // the favorit movie or actor has an enity in it or not
		 // we chose to do it here instead if the data base becuase
		 // then we can do less interactions between the font and the
		 // back end
		 if(datafavoritemovie.size()>0) {
			 favoritmoviefound = true;
		 }else {
			 favoritmoviefound = false;
		 }
		 
		 if(datafavoriteactor.size()>0) {
			 favoritactorfound = true;
		 }else {
			 favoritactorfound = false;
		 }
		 
		 //start defineing location and texts of all objects
		 
		 //start of labels
		 Updateuserinfolb.setForeground(white);
	     Updateuserinfolb.setOpaque(true);
	     Updateuserinfolb.setBackground(grey);
	     Updateuserinfolb.setText(" User Information");
	     Updateuserinfolb.setBounds(5*width/20-25, 180, 200, 50);
	     Updateuserinfolb.setFont(new Font("Courier", Font.BOLD,17));
	     
	     UpdateMovieinfolb.setForeground(white);
	     UpdateMovieinfolb.setOpaque(true);
	     UpdateMovieinfolb.setBackground(grey);
	     UpdateMovieinfolb.setText(" Movie Information");
	     UpdateMovieinfolb.setBounds(141*width/320-25, 180, 200, 50);
	     UpdateMovieinfolb.setFont(new Font("Courier", Font.BOLD,17));
	     
	     UpdateMoviefavoriteinfolb.setForeground(white);
	     UpdateMoviefavoriteinfolb.setOpaque(true);
	     UpdateMoviefavoriteinfolb.setBackground(grey);
	     UpdateMoviefavoriteinfolb.setText(" Favorite Movies");
	     UpdateMoviefavoriteinfolb.setBounds(141*width/320, 650, 150, 50);
	     UpdateMoviefavoriteinfolb.setFont(new Font("Courier", Font.BOLD,15));
	     
	     UpdateMovielikeinfolb.setForeground(white);
	     UpdateMovielikeinfolb.setOpaque(true);
	     UpdateMovielikeinfolb.setBackground(grey);
	     UpdateMovielikeinfolb.setText("  Liked Movies");
	     UpdateMovielikeinfolb.setBounds(141*width/320, 250, 150, 50);
	     UpdateMovielikeinfolb.setFont(new Font("Courier", Font.BOLD,15));
	     
	     UpdateActorinfolb.setForeground(white);
	     UpdateActorinfolb.setOpaque(true);
	     UpdateActorinfolb.setBackground(grey);
	     UpdateActorinfolb.setText(" People Information");
	     UpdateActorinfolb.setBounds(79*width/128, 180, 200, 50);
	     UpdateActorinfolb.setFont(new Font("Courier", Font.BOLD,17));
	     
	     UpdatefavoritActorinfolb.setForeground(white);
	     UpdatefavoritActorinfolb.setOpaque(true);
	     UpdatefavoritActorinfolb.setBackground(grey);
	     UpdatefavoritActorinfolb.setText(" Favorite Person");
	     UpdatefavoritActorinfolb.setBounds(81*width/128, 650, 150, 50);
	     UpdatefavoritActorinfolb.setFont(new Font("Courier", Font.BOLD,15));
	     
	     UpdatelikedActorinfolb.setForeground(white);
	     UpdatelikedActorinfolb.setOpaque(true);
	     UpdatelikedActorinfolb.setBackground(grey);
	     UpdatelikedActorinfolb.setText("  Liked People");
	     UpdatelikedActorinfolb.setBounds(81*width/128, 250, 150, 50);
	     UpdatelikedActorinfolb.setFont(new Font("Courier", Font.BOLD,15));
		 // ends the label
	     
	     // start buttons
	     UserNamebtn.setText("Change User \nName");
	     UserNamebtn.setBounds(3*width/10,height/2-160, 150, 50);
	     UserNamebtn.setBackground(yellow);
	     
	     Passwordbtn.setText("Change Password");
	     Passwordbtn.setBounds(3*width/10,height/2-60, 150, 50);
	     Passwordbtn.setBackground(yellow);
	     
	     UserRealNamebtn.setText("Change Real \nName");
	     UserRealNamebtn.setBounds(3*width/10,height/2+40, 150, 50);
	     UserRealNamebtn.setBackground(yellow);
	     
	     UserAgebtn.setText("Change User \nAge");
	     UserAgebtn.setBounds(3*width/10,height/2+140, 150, 50);
	     UserAgebtn.setBackground(yellow);
	     
	     deletebtn.setText("Delete Account");
	     deletebtn.setBounds(4*width/5, height/2-50, 200, 100);
	     deletebtn.setBackground(yellow);
		 
		 backbtn.setText("Back");
		 backbtn.setBounds(100, height/2-50, 200, 100);
		 backbtn.setBackground(yellow);
		 
		 instructionsbt.setText("Instructions");
		 instructionsbt.setBounds(650, height-150, 600, 75);
		 instructionsbt.setBackground(yellow);
		 instructionsbt.setFont(new Font("Courier", Font.BOLD,20));
	     // end buttons
		 
		 // start of fields
		 UserNamefld.setBounds(width/5, height/2-160, 150,50 );
	     UserNamefld.setBackground(teal);
	     UserNamefld.setText(currUser);
	     Border border = BorderFactory.createLineBorder(Color.ORANGE);
	     UserNamefld.setBorder(border);
	        
	     Passwordfld.setBounds(width/5, height/2-60, 150,50 );
	     Passwordfld.setBackground(teal);
	     Passwordfld.setText(currPassword);
	     Border border2 = BorderFactory.createLineBorder(Color.ORANGE);
	     Passwordfld.setBorder(border2);
	        
	     UserRealNamefld.setBounds(width/5, height/2+40, 150,50 );
	     UserRealNamefld.setBackground(teal);
	     UserRealNamefld.setText(Database.getRealName(currUser,currPassword));
	        
	     UserAgefld.setBounds(width/5, height/2+140, 150,50 );
	     UserAgefld.setBackground(teal);
	     UserAgefld.setText(Database.getAge(currUser,currPassword));
	     // end of fields
	     
	     // start of panes
	     likedactorpn.setBounds(81*width/128, 310, 150, 300);
	     favoritactorpn.setBounds(81*width/128, 710, 150, 100);
	     likedmoviepn.setBounds(141*width/320, 310, 150, 300);
	     favoritmoviepn.setBounds(141*width/320, 710, 150, 100);
	     // end of panes
	     
	     // start of action need when user interacts with program by clicking
	     
		 //start of the buttons
	     //this is the back button
	     // it removes all the objects from the frame and then 
	     // calls the log in home button
		 backbtn.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
				 inputframe.remove(instructionsbt);
				 inputframe.remove(deletebtn);
			     inputframe.remove(backbtn);
			     inputframe.remove(likedmoviepn);
			     inputframe.remove(favoritmoviepn);
			     inputframe.remove(likedactorpn);
			     inputframe.remove(favoritactorpn);
			     inputframe.remove(UpdatefavoritActorinfolb);
			     inputframe.remove(UpdatelikedActorinfolb);
			     inputframe.remove(UpdateActorinfolb);
			     inputframe.remove(UpdateMovielikeinfolb);
			     inputframe.remove(UpdateMoviefavoriteinfolb);
			     inputframe.remove(UpdateMovieinfolb);
			     inputframe.remove(Updateuserinfolb);
			     inputframe.remove(UserNamebtn);
			     inputframe.remove(Passwordbtn);
			     inputframe.remove(UserRealNamebtn);
			     inputframe.remove(UserAgebtn);
			     inputframe.remove(UserNamefld);
			     inputframe.remove(Passwordfld);
			     inputframe.remove(UserRealNamefld);
			     inputframe.remove(UserAgefld);
			     inputframe.remove(canvas);
			     loginhome(inputframe);

			 }
		 });
		 //this a very important
		 //the delete button connects to the databace and removes the current user
		 // then it removes all the objects from the frame
		 // and then it calls the methed that created the frame
		 // to log in with a new user
		 deletebtn.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){
                 int res = JOptionPane.showConfirmDialog(null, "Are you sure you would"
                 		+ " like to delete Your User Account?");
                 if(res==0) {
                	 Database.deleteUser(currUser,currPassword);
                	 inputframe.remove(instructionsbt);
                	 inputframe.remove(deletebtn);
    			     inputframe.remove(backbtn);
    			     inputframe.remove(likedmoviepn);
    			     inputframe.remove(favoritmoviepn);
    			     inputframe.remove(likedactorpn);
    			     inputframe.remove(favoritactorpn);
    			     inputframe.remove(UpdatefavoritActorinfolb);
    			     inputframe.remove(UpdatelikedActorinfolb);
    			     inputframe.remove(UpdateActorinfolb);
    			     inputframe.remove(UpdateMovielikeinfolb);
    			     inputframe.remove(UpdateMoviefavoriteinfolb);
    			     inputframe.remove(UpdateMovieinfolb);
    			     inputframe.remove(Updateuserinfolb);
    			     inputframe.remove(UserNamebtn);
    			     inputframe.remove(Passwordbtn);
    			     inputframe.remove(UserRealNamebtn);
    			     inputframe.remove(UserAgebtn);
    			     inputframe.remove(UserNamefld);
    			     inputframe.remove(Passwordfld);
    			     inputframe.remove(UserRealNamefld);
    			     inputframe.remove(UserAgefld);
    			     inputframe.remove(canvas);
                	 loginscreen(inputframe);
                 }
				
			 }
		 });
		 //the button checks to see if the new user name is already taken
		 // it gets the text typed in from the user field
		 //if its not already taken it repalces the user name
		 // and updates all the relationships with the new user name
		 UserNamebtn.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){ 
				 String UpdatedUserName = UserNamefld.getText();
				 settingchange = false;
				 if(!Database.verifyNewUser(UpdatedUserName)) {
					 JOptionPane.showMessageDialog(null, "I am sorry, The UserName "+UpdatedUserName+""
								+ " is aready taken. Please try another ");
					return;
				 }
				 Database.changeUsername(UpdatedUserName,currUser,currPassword);
				 settingchange = true;
				 currUser = UpdatedUserName;
				 JOptionPane.showMessageDialog(null, "Successfully Changed UserName to "+currUser);
			 }
		 });
		 // when clicked it places the text from the passwordfld
		 // is changed the current users password to the new password
		 Passwordbtn.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){ 
				 String Updatedpassword = Passwordfld.getText();
				 Database.changePassword(Updatedpassword,currUser,currPassword);
				 settingchange = true;
				 currPassword = Updatedpassword;
				 JOptionPane.showMessageDialog(null, "Successfully Changed Password to "+Updatedpassword);
			 }
		 });
		 // when clicked it gains the information from userrealnamefld
		 // then connects the to the data base and replaces the current users
		 // real name with this updated real name
		 UserRealNamebtn.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){ 
				 String UpdatedRealName = UserRealNamefld.getText();
				 Database.changeName(UpdatedRealName,currUser,currPassword);
				 settingchange = true;
				 JOptionPane.showMessageDialog(null, "Successfully Changed Real Name to "+UpdatedRealName);
			 }
		 });
		 // when clicked it gets the information from user age field and 
		 // checks to make sure it is an int
		 // if it is then it replaces the current users age with the typed in age
		 UserAgebtn.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){ 
				 String UpdatedUserage = UserAgefld.getText();
				 settingchange=false;
				 try {
					 int newage = Integer.parseInt(UpdatedUserage);
					 Database.changeAge(newage,currUser,currPassword);
					 settingchange=true;
					 JOptionPane.showMessageDialog(null, "Successfully Changed age to "+UpdatedUserage);
				 } catch(NumberFormatException ne) {
					 JOptionPane.showMessageDialog(null, "I am sorry, "+UpdatedUserage+""
								+ " is not an integer. Please try again ");
					 return;
				 }
			 }
		 });
		 // simple instructions button
		 // when clicked instructions on how to use the page apear
		 instructionsbt.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent e){ 
				 
				 JOptionPane.showMessageDialog(null, "TO UPDATE USERNAME/PASSWORD/REAL NAME/AGE \n1)"
				 		+ "Type in new information and click corresponding Submit(yellow) \n "
				 		+ "\nTO DELETE A LIKED MOVIE/PEOPLE \n"
				 		+ "1) Click on corresponding list"
				 		+ "\n2) Click on Movie/Person you wish to remove"
				 		+ "\n3) Click Yes on popup test box \n \n"
				 		+ "TO DELETE FAVORITE MOVIE/PERSON \n"
				 		+ "1) Click on Favorite Movie/Person "
				 		+ "\n2) Click Yes on popup test box\n \n"
				 		+ "TO ADD A NEW FAVORITE MOVIE/PERSON\n"
				 		+ "1) Delete corresponding Favorite Movie/Actor\n"
				 		+ "2) Click on your favorite Person/Movie from your Liked tab\n"
				 		+ "2) Click Add To Favorite? on popup test box",
				 		"Instructions", JOptionPane.INFORMATION_MESSAGE);
				
			 }
		 });
		 // end of buttons
		 
		 // start if panes
		 // the liked movie pane
		 // when the user clicks a movie in this pane two things can happen
		 // one) if the favorite movie is full, then it asks if the movie should be removed
		 // two) if the favorite movie is empty, then it asks if the movie should be added to the favorite
		 listslikedmovies.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     Object[] options = {"Yes","No", "Add to Favorites? "};
	                     int res = 4;
	                     if(!favoritmoviefound) {
		                      res = JOptionPane.showOptionDialog(null,"Would you like"
		                      		+ " to remove "+item.toString()+" from your Liked Movies","",
		                      		JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, options,options[0]); 

	                     }else {
		                      res = JOptionPane.showConfirmDialog(null, "Would you like"
		                      		+ " to remove "+item.toString()+" from your Liked Movies");
	                     }
	                     if(res==0) {
	                    	 Database.removeLikedMovie(((Pair)item).getKey(),currUser,currPassword);
	                    	 datalikedmovies.remove(item);
	                    	 listslikedmovies.setListData(datalikedmovies);
	                    	
	                     }else if(res==2 && !favoritmoviefound) {
	                    	 favoritmoviefound = true;
	                    	 Database.addFavMovie(currPassword,currUser,((Pair)item).getKey());
	                    	 datafavoritemovie.add(item);
	                    	 listsfavoritemovie.setListData(datafavoritemovie);
	                     };
	                  }
	               }
	            }
	         });
		// the liked actor pane
		// when the user clicks a actor in this pane two things can happen
		// one) if the favorite actor is full, then it asks if the actor should be removed
		// two) if the favorite actor is empty, then it asks if the actor should be added to the favorite
		 listslikedactor.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     Object[] options = {"Yes","No", "Add to Favorites? "};
	                     int res = 4;
	                     if(!favoritactorfound) {
		                      res = JOptionPane.showOptionDialog(null,"Would you like to remove"
		                      		+ " "+item.toString()+" from your Liked People?","",
		                      		JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, options,options[0]); 

	                     }else {
		                      res = JOptionPane.showConfirmDialog(null, "Would you like "
		                      		+ "to remove "+item.toString()+" from your Liked People?");
	                     }
	                     if(res==0) {
	                    	 Database.removeLikedPerson(((Pair)item).getKey(),currUser,currPassword);
	                    	 datalikedactor.remove(item);
	                    	 listslikedactor.setListData(datalikedactor);
	                    	
	                     }else if(res==2 && !favoritactorfound ) {
	                    	 favoritactorfound = true;
	                    	 Database.addFavPerson(currPassword,currUser,((Pair)item).getKey());
	                    	 datafavoriteactor.add(item);
	                    	 listfavoriteactor.setListData(datafavoriteactor); 
	                     };
	                  }
	               }
	            }
	         });
		 // when the movie is clicked it asks the user if they
		 // wish to remove the movie from the favorite list
		 listsfavoritemovie.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to remove "
	                     		+ ""+item.toString()+"from Your Favorite Movies?");
	                     if(res==0) {
	                    	 favoritmoviefound = false;
	                    	 Database.removeFavMovie(((Pair)item).getKey(),currUser,currPassword);
	                    	 datafavoritemovie.remove(item);
	                    	 listsfavoritemovie.setListData(datafavoritemovie);
	                     }
	                  }
	               }
	            }
	         });
		// when the actor is clicked it asks the user if they
		// wish to remove the actor from the favorite list
		 listfavoriteactor.addMouseListener(new MouseAdapter() {
	            public void mouseClicked(MouseEvent me) {
	               if (me.getClickCount() == 1) {
	                  JList target = (JList)me.getSource();
	                  int index = target.locationToIndex(me.getPoint());
	                  if (index >= 0) {
	                     Object item = target.getModel().getElementAt(index);
	                     int res = JOptionPane.showConfirmDialog(null, "Would you like to remove "
	                     		+ ""+item.toString()+"from Your Favorite Person?");
	                     if(res==0) {
	                    	 favoritactorfound = false;
	                    	 Database.removeFavPerson(((Pair)item).getKey(),currUser,currPassword);
	                    	 datafavoriteactor.remove(item);
	                    	 listfavoriteactor.setListData(datafavoriteactor);
	                     }
	                  }
	               }
	            }
	         });
		 
		 //this just allows it so when the user clicks the user fields
		 // it removes all the text in that field so the user can
		 //type it its own things
		 UserNamefld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		UserNamefld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	});
	    Passwordfld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		Passwordfld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	}); 
	    UserRealNamefld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		UserRealNamefld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	}); 
	    UserAgefld.addFocusListener(new FocusListener() {
	        	public void focusGained(FocusEvent e) {
	        		UserAgefld.setText(""); 
	        	}
	        	public void focusLost(FocusEvent e) {

	        	}
	        	});  
	    // just adds all the the object to the frames
		 inputframe.add( instructionsbt);
		 inputframe.add(deletebtn);
	     inputframe.add(backbtn);
	     inputframe.add(likedmoviepn);
	     inputframe.add(favoritmoviepn);
	     inputframe.add(likedactorpn);
	     inputframe.add(favoritactorpn);
	     inputframe.add(UpdatefavoritActorinfolb);
	     inputframe.add(UpdatelikedActorinfolb);
	     inputframe.add(UpdateActorinfolb);
	     inputframe.add(UpdateMovielikeinfolb);
	     inputframe.add(UpdateMoviefavoriteinfolb);
	     inputframe.add(UpdateMovieinfolb);
	     inputframe.add(Updateuserinfolb);
	     inputframe.add(UserNamebtn);
	     inputframe.add(Passwordbtn);
	     inputframe.add(UserRealNamebtn);
	     inputframe.add(UserAgebtn);
	     inputframe.add(UserNamefld);
	     inputframe.add(Passwordfld);
	     inputframe.add(UserRealNamefld);
	     inputframe.add(UserAgefld);
	     inputframe.add(canvas);
	     inputframe.pack();
	     inputframe.setVisible(true);
	 }
	 
	 // this is the map function
	 // it gains all the the data from the input of the movie object
	 // then it translates the degree cordanate to an x and y postion so 
	 // the paint method can place on the map with the actors where born in
		 public static void map(JFrame inputframe, Object Movie) {
			 Random rand = new Random();
			 framecount = 2;
			 inputframe.getContentPane().setBackground(Color.CYAN);
			 inputframe.setDefaultCloseOperation(inputframe.EXIT_ON_CLOSE);
			 inputframe.setTitle("Map Screen");
			 inputframe.setVisible(true);
			 
			 
			 double xoffset = -70;
			 double yoffset = 70;
			 //buttons
			 JButton backbtn = new JButton();
			 //text areas
			 JTextArea Informationfld = new JTextArea();
			 
			 //this is places all objects in the correct postion and with the correct text
			 // start buttons
			 backbtn.setText("Back");
			 backbtn.setBounds(width-300, height/2-50, 200, 100);
			 backbtn.setBackground(yellow);
			 // end buttons
			 //start fields
			 Informationfld.setForeground(white);
			 Informationfld.setOpaque(true);
			 Informationfld.setBackground(grey);
			 Informationfld.setText("Each Dot Represents\nan Actor's Birthplace\nThe"
			 		+ " Size of the dot\nrepresents the order\nbilled in the movie");
			 Informationfld.setBounds(30, 500, 250, 150);
			 Informationfld.setFont(new Font("Courier", Font.BOLD,15));
			 //end fields

			 //Get actors from the movie
			 String movieID = ((Pair)Movie).getKey();
			 //movieInfo.clear();
			 //movieInfo = Database.getMovieInfo(movieID);
			 //String actors = movieInfo.get(11).toString();
			 // we need to split the string of actors to get their Id's
			 //actors = actors.replaceAll(",\s+",",");
			 //String [] listofactors = actors.split(",");
			 ArrayList<ArrayList<Double>> coordinates = new ArrayList<ArrayList<Double>>();
			 coordinates.add(new ArrayList<Double>());// Index 0 for Latitude
			 coordinates.add(new ArrayList<Double>());// Index 1 for Longitude
			 ArrayList<Double> coordPair = new ArrayList<Double>();
			 ArrayList<Pair> actorsinMovie = Database.getActors(movieID);
			 //For each actor in the actors list retrieved using getActors
			 for (Pair a: actorsinMovie) {
			 	 //Now retrieve coordinates for the current actor as a coordinate pair
				 //Reassigned each actor
				 coordPair = Database.getCoordinates(a.getKey());
				 //Insert into coordinates array
				 if(coordPair != null) {
					 coordinates.get(0).add(coordPair.get(0));
					 coordinates.get(1).add(coordPair.get(1));
				 }else{
				 	System.out.println("The coordinate pair is null");
				 }
			 }
			 // coordinates should now have all the coordinate pairs for each actor

			 //Now transfer these coordinates to x-y values
			 if(coordinates.size() != 0) {
				 for (int i = 0; i < coordinates.get(0).size(); i++) {
					 double Latitude = coordinates.get(0).get(i);
					 double Longitude = coordinates.get(1).get(i);

					 // the further down we go the larger the y off set needs to be
					 yoffset = Math.sqrt((Math.abs(Latitude) + 180 - Math.abs(Longitude))) + 70;
					 if (Latitude < 0) {
						 yoffset = yoffset + 40;
					 }// Once xoffset and yoffset are updated, adjust values and add to arrays
					 longcord.add((double) (((180 + Longitude) * width / 360.0) + xoffset));
					 latcord.add((double) (height - ((90 + Latitude) * height / 180.0) + yoffset));
				 }
			 }else{
			 	System.out.print("Coordinate array size is 0");
			 }
			 // this is the array list the allen is going to place in the values out
			 //putted by the data ace
//	    	 ArrayList<Double> longtemp =new ArrayList<Double>();
//	    	 ArrayList<Double> lattemp = new ArrayList<Double>();
//	    	 longtemp.add(-119.417931);
//	    	 longtemp.add(-89.500000);
//	    	 longtemp.add(2.349014);
//	    	 longtemp.add(151.209900);
//	    	 longtemp.add(12.496366);
//	    	 longtemp.add(-153.369141);
//	    	 longtemp.add(46.460938);
//	    	 longtemp.add(-46.625290);
//	    	 longtemp.add(139.839478);
//	    	 lattemp.add(36.778259);
//	    	 lattemp.add(44.500000);
//	    	 lattemp.add(48.864716);
//	    	 lattemp.add(-33.865143);
//	    	 lattemp.add(41.902782);
//	    	 lattemp.add(66.160507);
//	    	 lattemp.add(-19.002846);
//	    	 lattemp.add(-23.533773);
//	    	 lattemp.add(35.652832);
//
//		     for(int i=0; i< lattemp.size();i++) {
//	    	 double longvale = longtemp.get(i);
//		    	 double latvale = lattemp.get(i);
//		    	 // the further down we go the larger the y off set needs to be
//		    	 yoffset = Math.sqrt((Math.abs(latvale)+ 180 -Math.abs(longvale)))+70;
//		    	 if(latvale<0) {
//		    		 yoffset = yoffset+40;
//		    	 }
//		    	 longcord.add((double) (((180 +longvale)*width/360.0)+xoffset));
//		    	 latcord.add((double) (height-((90+ latvale)*height/180.0)+yoffset));
//
//		     }
		    
		     Canvas canvas = new Drawing();
		     canvas.setSize(width, height);
		     // the back button to removes all the object 
		     // and calls the selected movie frame again
		     // it also clears all the array list so that way 
		     // next time you open the map it is only from the movie
		     // you currently clicked on
		     backbtn.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){
					 inputframe.remove(Informationfld);
					 inputframe.remove(backbtn);
				     inputframe.remove(canvas);
				     longcord = new ArrayList<Double>();
				     latcord =new ArrayList<Double>();
					 selectedMovie(inputframe, Movie);

				 }
			 });
		    // just adds all the object to the frame
		     inputframe.add(Informationfld);
		     inputframe.add(backbtn);
		     inputframe.add(canvas); 
		     inputframe.pack();
		     inputframe.setVisible(true);	        
		 }
		 
	}
