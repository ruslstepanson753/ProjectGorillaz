FROM postgres:latest

# Установка необходимых пакетов и загрузка демо-данных
RUN apt-get update -y && apt-get install -y curl unzip \
    && curl -o demo.zip https://edu.postgrespro.ru/demo-small.zip \
    && unzip demo.zip && rm demo.zip && mv demo*.sql demo.sql \
    && sed -i 's/DROP DATABASE demo/DROP DATABASE IF EXISTS demo;/g' demo.sql \
    && echo "CREATE DATABASE game;" >> create_game.sql \
    && mv demo.sql /docker-entrypoint-initdb.d/ \
    && mv create_game.sql /docker-entrypoint-initdb.d/

# Устанавливаем переменные среды для PostgreSQL
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=postgres
ENV POSTGRES_DB=postgres

# Открываем порт 5432
EXPOSE 5432