package com.application.expression;

import org.springframework.stereotype.Service;

@Service("authorizeBean")
public class MyExpression {
	// TODO : bug : can not accept express
	public boolean check(String input) throws Exception {
		System.err.println("check : " + input);
		if (input.equals("teonv"))
			return true;
		return false;
	}
}
