package com.r1.eligRules;

import com.r1.eligRules.examples.decisiontable.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Handler implements RequestHandler<Map<String,String>, String> {

    static final Logger LOG = LoggerFactory.getLogger(Handler.class);
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    
    public Handler() {
    }

    @Override
    public String handleRequest(Map<String,String> event, Context context) {
        LOG.info("in handleRequest, event = " + event.toString());
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession ksession = kContainer.newKieSession("TestKS");
        Person p = gson.fromJson(gson.toJson(event), Person.class);
        ksession.insert(p);
        ksession.fireAllRules();
        ksession.dispose();
        LOG.info("handleRequest completed");
        LOG.info("valid = " + p.getValid());
        return null;
    }
}
