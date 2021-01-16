package com.r1.rulesRepoService.models;

public class PersonRule {
    public String RuleName;
    public String ModelName;
    public String Condition;

    public PersonRule(String ruleName, String modelName, String condition) {
        this.RuleName = ruleName;
        this.ModelName = modelName;
        this.Condition = condition;
    }
}
