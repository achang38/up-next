# up-next
A movie suggestion application that recommends movies to the user based on the movies and actors they've liked in the past. This application was created because of our interest in finding new movies to watch. It was our final project for CS564 Database Management Systems at the University of Wisconsin - Madison. To learn about our [design process and testing](./project_info/FinalProjectPPTGroup4.pptx), to read our [final report](./project_info/FinalReportGroup4.docx), and to watch a [video demo](./project_info/UpNextWalkthrough.mp4).

## Setup
Running this application requires the installation of Java and MySQL. Using your editor for MySQL, you must first setup the database with:
```
create database movieapp;
use movieapp;
```
Then, run the code in [createtables.sql](./code/SQLfiles/createtables.sql). Then, edit the file paths [populatetables.sql](./code/SQLfiles/populatetables.sql) to match where the csv files are stored in your directory and run it. All the csv files are available [here](./datasets/cleaned_data) and depending on your MySQL server configurations, you may have to move the csv files to a folder designated by your server. Next, edit [JDBC.java](./code/src/JDBC.java) by changing the static variables to match your own credentials. Then add the jar in the [dependencies](./dependencies) folder to the dependencies of whatever editor you are using for Java. Finally, compile and run [Drawing.java](./code/src/Drawing.java).

## Authors
Allen Chang


Joel Foster


Logan Crooks
