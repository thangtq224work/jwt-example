//package com.application.exception.handler;
//
//import javax.transaction.SystemException;
//
//import org.hibernate.engine.spi.SessionImplementor;
//import org.hibernate.resource.transaction.backend.jta.internal.synchronization.ExceptionMapper;
//
//import com.application.exception.CustomContraintVialationException;
//
//public class ConstraintViolationExceptionMapper implements ExceptionMapper{
//
//	@Override
//	public RuntimeException mapStatusCheckFailure(String message, SystemException systemException,
//			SessionImplementor sessionImplementor) {
//		CustomContraintVialationException exception = new CustomContraintVialationException(message);
//		exception.setCode(systemException.errorCode);
//		return null;
//	}
//
//	@Override
//	public RuntimeException mapManagedFlushFailure(String message, RuntimeException failure,
//			SessionImplementor session) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
