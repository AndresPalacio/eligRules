package com.r1.eligRules.examples.decisiontable;

import org.drools.compiler.lang.DrlDumper;
import org.drools.compiler.lang.api.DescrFactory;
import org.drools.compiler.lang.api.PackageDescrBuilder;
import org.drools.template.model.Condition;
import org.drools.template.model.Rule;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

/**
 * This shows off a decision table.
 */
public class PricingRuleDTExample {

    public static final void main(String[] args) {
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieFileSystem kfs = KieServices.Factory.get().newKieFileSystem();
        System.out.println(kc.verify().getMessages().toString());
        addRule(kfs);
        execute( kc );
    }

    @SuppressWarnings("restriction")
    private static void addRule(KieFileSystem kieFileSystem) {
      PackageDescrBuilder packageDescrBuilder = DescrFactory.newPackage();
  
  
      packageDescrBuilder
              .name("com.sample.model")
              .newRule()
              .name("Is of valid age")
              .lhs()
  
              .pattern("Person").constraint("age < 18")
              .id("$a", true).end()
              //.pattern().id("$a", false).end()
              .end()
              .rhs("$a.setShowBanner( false );")
              //.rhs("insert(new Person())")
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
     }catch(Exception e){
         System.out.println(e);
     }
  
  
  }

    public static void execute( KieContainer kc ) {
        StatelessKieSession ksession = kc.newStatelessKieSession( "DecisionTableKS");

        //now create some test data
        Driver driver = new Driver();
        Policy policy = new Policy();

        ksession.execute( Arrays.asList( new Object[]{driver, policy} ) );

        System.out.println( "BASE PRICE IS: " + policy.getBasePrice() );
        System.out.println( "DISCOUNT IS: " + policy.getDiscountPercent() );

        policy.getBasePrice();
    }

}
