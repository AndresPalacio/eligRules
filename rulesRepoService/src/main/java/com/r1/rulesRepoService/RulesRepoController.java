package com.r1.rulesRepoService;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import com.r1.rulesRepoService.models.PersonRule;

import org.drools.compiler.lang.DrlDumper;
import org.drools.compiler.lang.api.DescrFactory;
import org.drools.compiler.lang.api.PackageDescrBuilder;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Rules")
public class RulesRepoController {

    @GetMapping("/{id}")
    public String read(@PathVariable String id) {
        return "hello world";
    }

    @PostMapping
    public void post() {
        // now trying with rule generated from code
        
        PersonRule pr = new PersonRule("rule 1", "Person", "age > 17");
    }

    // TODO: move to offline rule-generator service which reads the repo
    private static void addRule(PersonRule rule) {
        KieFileSystem kfs = KieServices.Factory.get().newKieFileSystem();
        PackageDescrBuilder packageDescrBuilder = DescrFactory.newPackage();

        packageDescrBuilder
                .name("com.r1.eligRules.examples.decisiontable")
                // .newImport().target("com.r1.eligRules.examples.decisiontable.Person").end()

                .newRule()
                .name(rule.RuleName)
  
                .lhs()
                .pattern(rule.ModelName).constraint(rule.Condition)
                .id("$a", true).end()
                .end()
                
                .rhs("$a.setValid(true);System.out.println(\"done with rule\");")
                .end();

    
        String rules = new DrlDumper().dump(packageDescrBuilder.getDescr());
    
        try{
          // create new file
          File file = new File("generated-rules/test.drl");
          file.createNewFile();
          FileWriter fw = new FileWriter(file.getAbsoluteFile());
          BufferedWriter bw = new BufferedWriter(fw);
          bw.write(rules);
          // close connection
          bw.close();
          System.out.println("Rule Created Successfully");

          KieBuilder kieBuilder = KieServices.Factory.get().newKieBuilder(kfs);
          System.out.println("build all rules");
          kieBuilder.buildAll();
          Results results = kieBuilder.getResults();
          System.out.println("builder has messages = " + results.hasMessages(Message.Level.ERROR));
          System.out.println("builder results are  = " + results.getMessages());
       }catch(Exception e){
           System.out.println(e);
       }
    }
}
