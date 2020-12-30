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

If you want to use the AWS Lambda deployment:

```apt install awscli```

## 2. Dependencies:

This project is built with Maven. Dependencies can be viewed and modified in pom.xml. Alternatively, you can use the Eclipse or VS Code Maven interfaces to manage your dependencies. A key dependency is Drools, from org.drools. The pom.xml specifies the version of drools in a parent node:

```xml
<parent>
    <groupId>org.drools</groupId>
    <artifactId>drools</artifactId>
    <version>7.47.0.Final</version>
</parent>
```


## 3. Build, Test, Deploy

### Build

```mvn compile```

### Test

```mvn test```

### Run a local execution

This command is temporary; it simply runs a sanity test to validate that maven, drools, and java are playing nicely together.

```sh
mvn exec:java -Dexec.mainClass="com.r1.eligRules.examples.DroolsExamplesApp"
```

### Deploy (package for deployment):

```mvn package```

This will create a .jar for the version specified in pom.xml of eligRules. Modify that version in the pom.xml section as shown:

```xml
<version>0.0.1-SNAPSHOT</version>
```

Explore the jar's contents with the jar command:

```jar tvf target/eligRules-0.0.1-SNAPSHOT```

### Deploy (package for AWS deployment):

```mvn package shade:shade```

***Configure your AWS account, set up an IAM, and configure your workspace on the aws console. Create at least one key-pair with rights to create, modify and execute a lambda (AWSLambda_FullAccess is about as high as you'd need). Then configure that key pair using aws configure.***

TODO: clean this section up; for now just logging it... Run the following to update or create the lambda. Note that you'll have to replace the ARN, even if configured already (that's another TODO mentioned in the shell script):

```chmod 777 ./aws-deploy.sh```
```./aws-deploy.sh```


