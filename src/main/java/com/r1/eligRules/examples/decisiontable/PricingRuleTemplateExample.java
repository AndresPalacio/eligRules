package com.r1.eligRules.examples.decisiontable;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This shows off a rule template where the data provider is a spreadsheet.
 * This example uses the same spreadsheet as the Decision table example ({@link PricingRuleDTExample})
 * so that you can see the difference between the two.
 * 
 * Note that even though they  use the same spreadsheet, this example is just
 * concerned with the data cells and does not use any of the Decision Table data.
 */
public class PricingRuleTemplateExample {

    public static void main(String[] args) {
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        execute( kc );
    }

    public static void execute( KieContainer kc ) {
        KieSession ksession = kc.newKieSession( "DTableWithTemplateKS" );

        //now create some test data
        Driver driver = new Driver();
        Policy policy = new Policy();

        ksession.insert(driver);
        ksession.insert(policy);

        ksession.fireAllRules();

        System.out.println("BASE PRICE IS: " + policy.getBasePrice());
        System.out.println("DISCOUNT IS: " + policy.getDiscountPercent());

        ksession.dispose();

        policy.getBasePrice();
    }
}
