# Rules Repository Service

*** An API for managing business rules. This compiles a set of rules to be consumed by the Drools rules engine.****

## This README consists of the following:
1. System Requirements
2. Dependencies
3. Build, Test, Deploy
4. Helpful links

## 1. System Requirements

Java (you may need to run as sudo or a super user):

```apt install default-jre && default-jdk```

Maven:

```apt install maven```

## 2. Dependencies

TODO: define this later

## 3. Build, Test, Deploy

### Build

```mvn compile```

### Test

```mvn test```

### Run a local execution

```sh
mvn spring-boot:run
```

Browse to http://localhost:8080/ and find your route.

### Deploy (package for deployment):

```mvn package```
