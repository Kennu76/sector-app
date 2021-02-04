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

Clone the Git repository to your local machine and once you have Docker run the following command from a terminal

> docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=password -d mysql

### flyway db migration

gradle flywayBaseline

gradle flywayMigrate

### angular 

npm install -g @angular/cli

ng serve