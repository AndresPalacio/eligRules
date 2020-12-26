package com.r1.eligRules;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Handler implements RequestStreamHandler{
	
    static final Logger LOG = LoggerFactory.getLogger(Handler.class);

    public static void main(String[] args) throws Exception {
        new Handler().handleRequest(System.in, System.out, null);
    }
    
	@Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        
        LOG.info("in handleRequest()");
        
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");

        Demographics demo = new Demographics(66);
        kSession.insert(demo);
        kSession.fireAllRules();
        
        LOG.info("completed handleRequest()");
    }
}