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
        // baseline example
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        System.out.println(kc.verify().getMessages().toString());
        execute( kc );        
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
