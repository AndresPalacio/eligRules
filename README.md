# Elig Rules

*A Drools-based library and framework to run eligibility rules*

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

## 2. Dependencies:

This project is built with Maven. Dependencies can be viewed and modified in pom.xml. Alternatively, you can use the Eclipse or VS Code Maven interfaces to manage your dependencies.

A key dependency is Drools, from org.drools. The pom.xml specifies the version of drools as:

```xml
<drools-version>7.47.0.Final</drools-version>
```


## 3. Build, Test, Deploy

### Build

```mvn compile```

### Test

```mvn test```

### Deploy (package for deployment):

```mvn package```

This will create a fat .jar (i.e. one with all dependencies included) for the version specified in pom.xml of eligRules. (TODO: figure out a way to make fat jar optional) Modify that version in the pom.xml section as shown:

```xml
<version>0.0.1-SNAPSHOT</version>
```

Explore the jar's contents with the jar command:

```jar tvf target/eligRules-0.0.1-SNAPSHOT```

### Deploy to AWS Lambda:

```mvn package shade:shade```
