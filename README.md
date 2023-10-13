docker run -d --name spring-data-jpa-course -p 5432:5432 -e POSTGRES_PASSWORD=pass123 postgres:16-alpine

docker exec -it spring-data-jpa-course bash

psql localhost -U postgres

CREATE DATABASE jpa;
ALTER USER "postgres" WITH password 'password';

\c jpa
\dt
\d student