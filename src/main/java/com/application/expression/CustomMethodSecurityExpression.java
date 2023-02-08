package com.application.expression;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpression extends SecurityExpressionRoot implements MethodSecurityExpressionOperations{
	
	public CustomMethodSecurityExpression(Authentication authentication) {
		super(authentication);
	}

	private Object filterObject;
    private Object returnObject;
    private Object target;
    
    public boolean makeDecision(String input) {
    	if(input.equals("teonv")) return true;
    	return false;
    }
    public boolean makeDecision() {
    	return true;
    }
	
	@Override
	public void setFilterObject(Object filterObject) {
		this.filterObject = filterObject;
		
	}

	@Override
	public Object getFilterObject() {
		return this.filterObject;
	}

	@Override
	public void setReturnObject(Object returnObject) {
		this.returnObject = returnObject;
	}

	@Override
	public Object getReturnObject() {
		return this.returnObject;
	}

	@Override
	public Object getThis() {
		return target;
	}
	
}
