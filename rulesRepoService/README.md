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

MongoDB:

Use documentation from [docs.mongodb.com](https://docs.mongodb.com) to install MongoDB for your operating system.

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

Browse to [http://localhost:8080/](http://localhost:8080) and find your route. You can configure your route in application.properties with:

```server.port=8080```

If running against a local mongo, ensure it is started with:

```sh
sudo systemctl start mongod
```

Validate mongo has started with:

```sh
sudo systemctl status mongod
```

You may want to configure your mongo to point to a specific instance, either locally or otherwise. You can do this again in the application.properties using the following properties:

```
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=rulesRepo
```

These properties are read by the Mongo library implicitly (i.e by convention). There are additional properties for username and password, which you'll need to configure appropriately in your "admin" Mongo database:

```
spring.data.mongodb.username=user
spring.data.mongodb.password=secret
```

### Deploy (package for deployment):

```mvn package```

TODO: learning on spring + war deployment.
