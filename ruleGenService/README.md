# Rule Generation Service

*** An offline process that reads business rules from a repository, compiles them into Drools .drl files, and packages them into a jar.****

## This README consists of the following:
1. System Requirements
2. Dependencies
3. Build, Test, Deploy
4. Helpful links

## 1. System Requirements

Java runtime and software development kit (you may need to run as sudo or a super user):

```apt install default-jre default-jdk```

Maven:

```apt install maven```

MongoDB:

Use documentation from [docs.mongodb.com](https://docs.mongodb.com) to install MongoDB for your operating system.

## 2. Dependencies

### MongoDB Collection:

TODO - How to tie the rule to the below BOM sample...

### Business Object Model Sample

TODO - figure out what this means... what if there are a bunch?

## 3. Build, Test, Deploy

### Build

```mvn clean compile```

### Test

```mvn test```

### Run a local execution

Package this into a .jar because this is an offline process. Use the shade plugin to include all the right jars:

```sh
mvn package shade:shade
```

This will create a .jar for the version specified in pom.xml of ruleGenService. Modify that version in the pom.xml section as shown below to change the version:

```xml
<version>0.0.1-SNAPSHOT</version>

To execute, use the jar with the version specified.
```

```sh
java -jar target/ruleGenService-0.0.1-SNAPSHOT.jar
```


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
