# sector-app

Welcome to my web-app! 

On my page you can add your name, the many, many sectors you are affiliated with and if you accept the (invisible) terms!

Additionally, once you have added some data to the DB (like the first "user") then you can go ahead and edit that users information. Thought you were into Textiles? Well now you're in Software and Hardware! Sorry!

Anyway...

This a full-stack project built on Java on Spring on the back-end side with Angular on the front-end.
For simplicity we will use MySQL as our database which will be run on a Docker container.

### Prerequisites 

You need to install a couple of things on your local machine in order to run this application

- [Docker](https://www.docker.com/get-started). ()
- [Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (version 8 or 11)
- [Gradle](https://gradle.org/install/) (version 5 or newer)
- [Node](https://nodejs.org/en/) (version 14 or newer)

With Node comes 'npm' which we'll use to download Angular's CLI to easily run our front-end.

### Setting up the database

Once you have Docker run the following command from a terminal
```console
> docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=password -d mysql
```

Now you should have a running MySQL server under `localhost:3306` on a Docker container.

Database config info is at
> sector-app\src\main\resources\application.properties

Go ahead and `git clone` the repository, open a terminal window and go to the repositories root folder (`sector-app`).
### flyway db migration

Database scripts and changes are made using migrations with [Flyway](https://flywaydb.org/). 
Thus you can find all the starting SQL scripts in the following path:

>sector-app\src\main\resources\db\migration

 And the files are
 
> V1__create_sectors_and_populate.sql

> V2__create_user_and_user_sectors.sql

Before starting the application you should first apply all the SQL scripts and you can do it with the following terminal commands in the root folder.

```console
> ./gradlew flywayBaseline
> ./gradlew flywayMigrate
```

### Back-end

Run the following command to install all the dependencies and the run the Spring app.

```console
> ./gradlew bootRun
```
And voila, your backend is now running on `localhost:8080` 

### Front-end

Open a new terminal window, from the root folder (`sector-app`) go to the `angular-app` folder with

```console
> cd angular-app
```

Then install all the Angular dependencies (including its CLI) with

```console
> npm install
```

And then this command to run it


```console
> ng serve
```

Now the front-end application is running on `localhost:4200`. 

Open that URL on any browser and that's it! Start adding people from different sectors TODAY!



### Engineers comments

The more complex bits for this assignment was figuring out a database schema for sectors with underlying subsectors which also have subsubsectors and so on. And how do you attach sectors to users that have selected them if they are able to select any type of sector. Because of this, the database schema is, for me atleast, a bit "unorthodox" especially the UserSector table.

Another complicated bit was creating a select box for the user where he/she can select multiple sectors. Luckily I got some "help" from the internet. 
[This stackOverFlow answer](https://stackoverflow.com/a/63236469) and subsequent sample code was used of the form's Sector selection part.


