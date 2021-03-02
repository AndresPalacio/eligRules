package com.r1.eligRules;

import java.util.Map;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.r1.r1RuleModels.Person;

public class Handler implements RequestHandler<Map<String,String>, String> {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public Handler() {
    }

    @Override
    public String handleRequest(Map<String,String> event, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("in handleRequest, event = " + event.toString());
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("TestKS");
        Person p = gson.fromJson(gson.toJson(event), Person.class);
        ksession.insert(p);
        ksession.fireAllRules();
        ksession.dispose();
        return Boolean.toString(p.getValid());
    }
}
