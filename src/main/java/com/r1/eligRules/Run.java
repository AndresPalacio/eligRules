package com.r1.eligRules;

import java.util.HashSet;
import java.util.Set;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.definition.KiePackage;
import org.kie.api.definition.rule.Rule;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Run {

	static final Logger LOG = LoggerFactory.getLogger(Run.class);
	
	public static void main(String[] args) {
        KieServices kieServices = KieServices.Factory.get();

        KieContainer kContainer = kieServices.getKieClasspathContainer();
        Results verifyResults = kContainer.verify();
        for (Message m : verifyResults.getMessages()) {
            LOG.info("{}", m);
        }

        LOG.info("Creating kieBase");
        KieBase kieBase = kContainer.getKieBase();

        LOG.info("There should be rules: ");
        for ( KiePackage kp : kieBase.getKiePackages() ) {
            for (Rule rule : kp.getRules()) {
                LOG.info("kp " + kp + " rule " + rule.getName());
            }
        }

        LOG.info("Creating kieSession");
        KieSession session = kieBase.newKieSession();

        LOG.info("Populating globals");
        Set<String> check = new HashSet<String>();
        session.setGlobal("controlSet", check);

        LOG.info("Now running data");

        Measurement mRed= new Measurement("color", "red");
        session.insert(mRed);
        session.fireAllRules();

        Measurement mGreen= new Measurement("color", "green");
        session.insert(mGreen);
        session.fireAllRules();

        Measurement mBlue= new Measurement("color", "blue");
        session.insert(mBlue);
        session.fireAllRules();

        LOG.info("Final checks");
        
        System.out.println("working memory size is " + session.getObjects().size());

	}

}
