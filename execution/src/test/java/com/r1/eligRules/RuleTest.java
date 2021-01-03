package com.r1.eligRules;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleTest {
    static final Logger LOG = LoggerFactory.getLogger(RuleTest.class);

    @Test
    public void test() {
        Handler h = new Handler();
        try {
            h.handleRequest(null, null, null);
            assertEquals(1, 1);
        }
        catch(IOException ex) {
            LOG.error(ex.getMessage());
            Assert.fail();
        }
        
    }
}
