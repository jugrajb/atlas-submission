## NOTE
- This project is only for submission purposes for CPSC 304
- This project is NOT runnable if no AWS credentials are passed to AWSConfig

## Setup Instructions Local

**Docker**
- docker pull postgres
- docker run --name atlas-db -e POSTGRES_PASSWORD=admin -d -p 5432:5432 postgres:alpine
- docker exec -it atlas-db bash
- psql -U postgres
- CREATE DATABASE atlasdb
- \c atlasdb

**Start Server**
- java -jar atlas-0.0.1-SNAPSHOT.jar

## Project Details
- Server Port: 8080
- DB Port: 5432
- RDBMS: postgresql
- DB Migration Manager: Flyway
- JDBC datasource: Hikari


