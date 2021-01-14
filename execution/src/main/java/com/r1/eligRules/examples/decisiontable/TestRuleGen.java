package com.r1.eligRules.examples.decisiontable;

import org.drools.compiler.lang.DrlDumper;
import org.drools.compiler.lang.api.DescrFactory;
import org.drools.compiler.lang.api.PackageDescrBuilder;
import org.drools.template.model.Condition;
import org.drools.template.model.Rule;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

public class TestRuleGen {

    public static final void main(String[] args) {

        // now trying with rule generated from code
        KieFileSystem kfs = KieServices.Factory.get().newKieFileSystem();
        addRule(kfs);
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession ksession = kc.newKieSession("TestKS");
        Person p = new Person();
        p.setAge(25);
        ksession.insert(p);
        System.out.println("fire all rules");
        ksession.fireAllRules();
        ksession.dispose();
        System.out.println("valid = " + p.getValid());
        
    }

    private static void addRule(KieFileSystem kieFileSystem) {
        PackageDescrBuilder packageDescrBuilder = DescrFactory.newPackage();
    
    
        packageDescrBuilder
                .name("com.r1.eligRules.examples.decisiontable")
                // .newImport().target("com.r1.eligRules.examples.decisiontable.Person").end()

                .newRule()
                .name("Is of valid age")
  
                .lhs()
                .pattern("Person").constraint("1 == 1")
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
