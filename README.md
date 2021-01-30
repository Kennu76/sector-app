# sector-app

docker run --name postgresql-container -p 5432:5432 -e POSTGRES_PASSWORD=somePassword -d postgres


### docker mysql
docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=password -d mysql

### flyway db migration

gradle flywayBaseline

gradle flywayMigrate

