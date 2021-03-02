# ELIG RULES

***A Drools-based rules execution engine, able to run on AWS Lambda. These rules focus on Patient/Guarantor eligibility.***

## Rule Creation

Check out the "rules-repo" directory. This is an API which compiles a JAR with Drools business rules content, specific to Eligibility.

## Rule Execution

Check out the "execution" directory. This compiles to a JAR which can be deployed to AWS Lambda.

## Complete Execution Flow:

The below covers the integration between the services and components. Executing the below should allow a developer or tested to create a rule in the "rules repo", generate the corresponding rule file, integrate that into the "execution" package, and deploy it to an aws lambda.

***Out of scope:

* System dependencies - covered in each service directory's README
* Custom configuration of Mongo, Spring, etc
* AWS configuration - covered in the "execution" service
* AWS lambda execution - TODO: may pop out a sample app that executes the lambda test cases.***

1. Run the rulesRepoService

Run MongoDB locally:

```sh
sudo systemctl start mongod
```

From the rulesRepoService directory, build, package and run spring-boot via maven:

```sh
mvn clean compile
```

```sh
mvn spring-boot:run
```

Validate it is running by browsing to [http://localhost:8080/rules](http://localhost:8080/rules)

POST a rule to your repository, for example:

```sh
curl -d '{"condition": "age >= 18", "ruleName": "over-18", "object": "Person"}' -X POST http://localhost:8080/rules -H "Content-Type:application/json"
```

Now generate the rule file using ruleGenService. From the ruleGenService directory, execute the Jar:

```sh
mvn clean compile package shade:shade
java -jar target/ruleGenService-0.0.1-SNAPSHOT.jar
```

From the execution directory, copy over the file (or files, if multiple rules posted) from the ruleGenService using the shell script (this is a step that will be replaced by some S3 or cloud storage solution):

```sh
get-generated-rule-files.sh
```

Clean, compile, package, and deploy your new lambda:

```sh
mvn clean compile package shade:shade
./aws-deploy.sh
```

Your lambda should now exist (or be updated) in your AWS console.
