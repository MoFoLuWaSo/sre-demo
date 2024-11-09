**This document contains confidential and proprietary information of Cardinal Financial Company, LP and cannot be 
reproduced, distributed, shared or printed without written permission from the company.**
# Senior Site Reliability Engineer Challenge

A development team would like help in containerizing their project.  They have a simple REST application that serves 
data from a PostgreSQL database.

## Objective 1: 
Containerize the Java test suite using Docker.

### Task Description: 
Create a Dockerfile that packages the test suite into a Docker container. This Dockerfile should handle compiling the test suite and setting it up to run upon container startup.

### Requirements:

- Select an appropriate Java base image.
- The container should be configured to run the test suite automatically at startup.
- Ensure test results can be easily retrieved or viewed (consider volume mounting for test reports).

## Objective 2: 
Develop a Helm chart to deploy the test suite and a database service in Kubernetes.

### Task Description:
The Helm chart should define Kubernetes resources for both the test suite container and a PostgreSQL database. The chart should ensure the database is ready before running the test suite, possibly using init containers or Kubernetes jobs.

### Requirements:

- Resources like Deployment, Service, and PersistentVolumeClaim for the database should be defined.
- Use ConfigMaps, Secrets, or environment variables for database configuration.
- Include a Kubernetes Job or a Pod with the test suite container that runs once the database is initialized.
- Provide clear instructions for deployment and accessing test results.

There is no time restriction.  We encourage you to implement as much as you can.

## Deliverables:

- A Merge request on this repo containing:
  - A dockerfile from objective 1
  - A helm chart and README from objective 2


# Background on the Program
## Prerequisites

Java is required.  We recommend Java 11 and use Corretto locally:
https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html

## Gradle - build structure

Gradle can be run with `./gradlew <command>` (or `gradlew.bat` on windows), it will download the required dependencies 
for this project.

You can run the program with `./gradlew bootRun`.  This requires the database to exist as described in the Postgres 
subsection.

Gradle commands can be chained and sometimes have dependencies.  To see a list of gradle commands run `gradlew tasks`.
The important tasks here are:
1. `flywayClean` - cleans the database so that it can be re-initialized
1. `flywayMigrate` - runs the database setup scripts located in `src/resources/db/migration`
1. `build` - builds the application
1. `test` - runs the applications unit tests
1. `clean` - cleans the build cache

For example, to do a fresh run:
`gradlew flywayMigrate build test`

Will initialize the database,  build the application and run its tests.

## Postgres

This project should be compatible with any SQL database, but has been tested with PostgreSQL.  An off the shelf docker 
image can be found here:

[Postgres Docker Page](https://hub.docker.com/_/postgres)

This has been tested with PostgreSQL **12.1**, which a tag currently exists for.

```bash
docker run --name some-postgres -p 5432:5432 postgres:12.1
```

**Note**: There are two files controlling access to the postgres database, both allow overriding the host name by defining 
`POSTGRES_HOST`.  The two files are:
1. src/main/resources/application.properties
1. build.gradle (flyway block)

### Flyway

Flyway is responsible for initializing the database schema.  It expects the user `postgres` to exist without a password.

The database **structure** can be initialized with `./gradlew flywayMigrate`.  It requires an existing database on port
`5432`.  If needed, `flywayClean` will clear the current schema so `flywayMigrate` will re-create it when run.

## Rest

A single REST endpoint is available at `http://localhost:8080/parts`.  The unit tests have been configured to test 
against a random port.

# Resources

Postgres docker image is here:
1. https://hub.docker.com/_/postgres
