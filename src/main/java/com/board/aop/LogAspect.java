package com.board.aop;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.board.aop.LogAspect;

@Aspect
@Component
public class LogAspect {
	public static Logger logger = Logger.getLogger(LogAspect.class.getName());
	public static String logMsg = "System Log::";

	@Around("within(com.bf..*)")
	public Object advice(ProceedingJoinPoint joinPoint) {
		Object object = null;
		try {
			logger.info(logMsg + joinPoint.getSignature().toString());
			object = joinPoint.proceed();
		} catch (Throwable e) {
			logger.severe(logMsg + e.getMessage());
			e.printStackTrace();
		}
		return object;
	}
	
	// logger method
	public static void info(Object obj) {
		logger.info(logMsg + String.valueOf(obj));
	}

	public static void warning(Object obj) {
		logger.warning(logMsg + String.valueOf(obj));
	}

	public static void severe(Object obj) {
		logger.severe(logMsg + String.valueOf(obj));
	}
}
