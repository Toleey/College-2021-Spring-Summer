package edu.bw.Spring.aop.demo1.logger;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

/**
 * 增强处理
 */
public class UserServiceLogger {
    private Logger logger = Logger.getLogger(UserServiceLogger.class);
    //日志的前置增强
    public void before(JoinPoint jp){
        logger.info("调用"+jp.getTarget()+"的"+jp.getSignature().getName()+"方法,"+"参数列表"+ Arrays.toString(jp.getArgs()));
    }
    //日志的后置增强
    public void afterReturning(JoinPoint jp,Object result){
        logger.info("调用"+jp.getTarget()+"的"+jp.getSignature().getName()+"方法，"+"返回值:"+result);
    }
}
