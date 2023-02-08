package com.application.expression;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class CustomMethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {
	@Override
	protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication,
			MethodInvocation invocation) {
		CustomMethodSecurityExpression root = new CustomMethodSecurityExpression(authentication);
		root.setPermissionEvaluator(getPermissionEvaluator());
		root.setTrustResolver(new AuthenticationTrustResolverImpl());
		root.setRoleHierarchy(getRoleHierarchy());
		return root;
	}
}
