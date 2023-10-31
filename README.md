# Spring boot Data JPA

Spring boot Data JPA is Spring boot application which
covers basic and advanced Spring boot Data JPA concepts :

- What is Spring Data JPA
- Connect to a real database and not in memory DB
- How to map classes to tables
- Usage of Annotations : @Entity, @Id, @SequenceGenerator, @GeneratedValue,
  @Column, @UniqueConstraint
- Usage of JpaRepository interface
- Usage of basic Spring Data JPA methods - find, delete, save
- Spring Data JPA custom methods
- Usage of Annotations : @Query, @Param,
- Hibernate Entity Life Cycle
- Paging and Sorting
- 1 to 1 Relationships
  1 to Many Relationships
  Many to Many relationships
- Transactions

Useful commands for development :

docker run -d --name spring-data-jpa-course -p 5433:5432 -e POSTGRES_PASSWORD=pass123 postgres:16-alpine

docker exec -it spring-data-jpa-course bash

docker start spring-data-jpa-course

psql -h localhost -U postgres

CREATE DATABASE jpa;
ALTER USER "postgres" WITH password 'password';

\c jpa
\d
\dt
\d student