# Hibernate final Project

### Instructions for launching:
1. Выполните команду в терминале для создания образа (из директории с Dockerfile):
docker build -t db_postgres_stepanov .
2. Чтобы запустить контейнер с пробросом порта 5432, выполните:
docker run -d --name my_postgres -p 5432:5432 db_postgres_stepanov
#### _2025 JRU Gorillaz, Stepanov Ruslan._