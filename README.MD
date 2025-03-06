# Hibernate final Project

### Instructions for launching:

1. Enter command in terminal:
docker build -t db_postgres_stepanov "Database Docker File/"

2. Enter command in terminal:
docker run -d --name my_postgres -p 5432:5432 db_postgres_stepanov

3. Enter command in terminal:
docker build -t tomcat_run_stepanov .

4. Enter command in terminal:
docker run -d --name tomcat_run -p 80:8080 tomcat_run_stepanov

#### _2025 JRU Gorillaz, Stepanov Ruslan._