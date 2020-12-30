package com.r1.eligRules.examples;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;
import com.r1.eligRules.examples.decisiontable.PricingRuleDTExample;
import com.r1.eligRules.examples.decisiontable.PricingRuleTemplateExample;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DroolsExamplesApp extends JFrame {

    public static void main(String[] args) {
        // DroolsExamplesApp droolsExamplesApp = new DroolsExamplesApp();
        // droolsExamplesApp.pack();
        // droolsExamplesApp.setVisible(true);
    }

    // protected final transient Logger logger = LoggerFactory.getLogger(getClass());

    // private final KieServices ks = KieServices.get();

    // private final KieContainer kieContainer;

    // public DroolsExamplesApp() {
    //     super("JBoss BRMS examples");
    //     setContentPane(createContentPane());
    //     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //     logger.info("DroolsExamplesApp started.");
    //     kieContainer = createKieContainer();
    // }

    // private KieContainer createKieContainer() {
    //     KieContainer kc = ks.getKieClasspathContainer();
    //     System.out.println(kc.verify().getMessages().toString());
    //     return kc;
    // }

    // private Container createContentPane() {
    //     JPanel contentPane = new JPanel(new GridLayout(0, 1));
    //     contentPane.add(new JLabel("Which GUI example do you want to see?"));

    //     contentPane.add(new JButton(new AbstractAction("PricingRuleTemplateExample") {
    //         public void actionPerformed(ActionEvent e) {
    //             PricingRuleTemplateExample.execute( kieContainer );
    //         }
    //     }));
    //     contentPane.add(new JButton(new AbstractAction("PricingRuleDTExample") {
    //         public void actionPerformed(ActionEvent e) {
    //             PricingRuleDTExample.execute( kieContainer );
    //         }
    //     }));
    //     return contentPane;
    // }

}
