package com.r1.ruleGenService;

import org.drools.compiler.lang.DrlDumper;
import org.drools.compiler.lang.api.DescrFactory;
import org.drools.compiler.lang.api.PackageDescrBuilder;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.mongodb.MongoClient;


public class Main {

    public static final void main(String[] args) {
        System.out.println("hello world");

        // get rules from mongo into the RuleRepo
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        mongoClient.getDatabaseNames().forEach(System.out::println);
        long docs = mongoClient.getDatabase("rulesRepo").getCollection("rule").count();
        System.out.println("# docs = " + docs);


        mongoClient.close();

        // generate a file for each rule
        

        // compile those files into something that the lambda can use
    }

    private static void addRule(KieFileSystem kieFileSystem) {
        PackageDescrBuilder packageDescrBuilder = DescrFactory.newPackage();
    
    
        packageDescrBuilder
                .name("com.r1.eligRules.examples.decisiontable")
                // .newImport().target("com.r1.eligRules.examples.decisiontable.Person").end()

                .newRule()
  
                .lhs()
                .pattern("Person").constraint("age >= 18")
                .id("$a", true).end()
                .end()
                
                .rhs("$a.setValid(true);System.out.println(\"done with rule\");")
                .end();

    
        String rules = new DrlDumper().dump(packageDescrBuilder.getDescr());
    
        try{
          // create new file
          File file = new File("src/main/resources/com/r1/eligRules/examples/decisiontable/test.drl");
          file.createNewFile();
          FileWriter fw = new FileWriter(file.getAbsoluteFile());
          BufferedWriter bw = new BufferedWriter(fw);
          bw.write(rules);
          // close connection
          bw.close();
          System.out.println("Rule Created Successfully");

          KieBuilder kieBuilder = KieServices.Factory.get().newKieBuilder(kieFileSystem);
          System.out.println("build all rules");
          kieBuilder.buildAll();
          Results results = kieBuilder.getResults();
          System.out.println("builder has messages = " + results.hasMessages(Message.Level.ERROR));
          System.out.println("builder results are  = " + results.getMessages());
       }catch(Exception e){
           System.out.println(e);
       }
    }
    
}
