package com.r1.eligRules;

import com.r1.eligRules.examples.decisiontable.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.kie.api.KieServices;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

public class Handler implements RequestStreamHandler {
    protected final transient Logger logger = LoggerFactory.getLogger(getClass());

    public Handler() {

        
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        logger.info("Handler initialized");
        KieServices kieServices = KieServices.get();
        logger.info("kieServices = " + kieServices.toString());
        KieContainer kContainer = kieServices.getKieClasspathContainer();
        Results verifyResults = kContainer.verify();
        PricingRuleTemplateExample.execute( kContainer );
        logger.info("handleRequest completed");
    }
}
