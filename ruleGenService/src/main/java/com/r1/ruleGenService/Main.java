package com.r1.ruleGenService;

import org.bson.Document;
import org.drools.compiler.lang.DrlDumper;
import org.drools.compiler.lang.api.DescrFactory;
import org.drools.compiler.lang.api.PackageDescrBuilder;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import com.r1.r1RuleModels.*;

public class Main {

    public static final void main(String[] args) {

        com.r1.r1RuleModels.R1Rule r = new com.r1.r1RuleModels.R1Rule();
        r.ruleName = "hello world";
        System.out.println(r.ruleName);

        // connect to rulesRepo
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        MongoCollection<Document> coll = mongoClient.getDatabase("rulesRepo").getCollection("rule");

        // collect the rules in a list
        List<Document> rules = new ArrayList<Document>();
        try (MongoCursor<Document> cursor = coll.find().iterator()) {
            while (cursor.hasNext()) {
                rules.add(cursor.next());
            }
        }

        mongoClient.close();

        // add a rule file for each rule
        rules.forEach(doc -> {
            addRule(doc);
        });
        
        // compile those files together (TBD - how do we do this?)
    }

    private static void addRule(Document rule) {
        KieFileSystem kfs = KieServices.Factory.get().newKieFileSystem();

        PackageDescrBuilder packageDescrBuilder = DescrFactory.newPackage();
    
    
        packageDescrBuilder
                .name("com.r1.eligRules")
                .newImport().target("com.r1.r1RuleModels.*").end()
                .newRule()
                .name(rule.get("ruleName").toString())
  
                .lhs()
                .pattern(rule.get("object").toString()).constraint(rule.get("condition").toString())
                .id("a", true).end()
                .end()
                
                .rhs("a.setValid(true);")
                .end();

    
        String rules = new DrlDumper().dump(packageDescrBuilder.getDescr());
    
        try{
          // create new file
          File file = new File(String.format("src/main/resources/%s.drl", 
                        rule.get("ruleName").toString().replace(' ', '-')));
          file.createNewFile();
          FileWriter fw = new FileWriter(file.getAbsoluteFile(), false);
          BufferedWriter bw = new BufferedWriter(fw);
          bw.write(rules);

          // close file connection
          bw.close();

          // build the rules (TODO - use the builder to determine if there are errors)
          KieBuilder kieBuilder = KieServices.Factory.get().newKieBuilder(kfs);
          kieBuilder.buildAll();
       }catch(Exception e){
           System.out.println(e);
       }
    }
    
}
