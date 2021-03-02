package com.r1.eligRules;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.GsonBuilder;
import com.r1.r1RuleModels.Person;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandlerTests {

    static final Logger LOG = LoggerFactory.getLogger(TestContext.class);

    @Test
    public void test_sanity_personAgeOld() {
        Handler h = new Handler();
        TestContext context = new TestContext();
        Map<String, String> obj = new HashMap<String, String>();
        obj.put("age", "999");
        String res = h.handleRequest(obj, context);
        context.getLogger().log(res);
        assertTrue("age is old", Boolean.parseBoolean(res));
        
    }

    @Test
    public void test_sanity_personAgeYoung() {
        Handler h = new Handler();
        TestContext context = new TestContext();
        Map<String, String> obj = new HashMap<String, String>();
        obj.put("age", "1");
        String res = h.handleRequest(obj, context);
        context.getLogger().log(res);
        assertFalse("age is young", Boolean.parseBoolean(res));
        
    }
}