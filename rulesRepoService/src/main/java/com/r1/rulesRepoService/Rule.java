
package com.r1.rulesRepoService;

import org.springframework.data.annotation.Id;

public class Rule {

	@Id private String id;

	private String condition;
	private String object;
	private String ruleName;

	public String getCondition() {
		return this.condition;
	}

	public void getCondition(String condition) {
		this.condition = condition;
	}

	public String getObject() {
		return this.object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getRuleName() {
		return this.ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	@Override
	public String toString() {
		return String.format("id=%s, object=%s, name=%s", 
		this.id, this.object, this.ruleName);
	}
}