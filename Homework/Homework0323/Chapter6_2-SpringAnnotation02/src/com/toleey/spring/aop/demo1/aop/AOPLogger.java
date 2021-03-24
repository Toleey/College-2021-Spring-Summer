package com.toleey.spring.aop.demo1.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
public class AOPLogger {

    private static Logger logger = Logger.getLogger(AOPLogger.class);

    //定义切入点方法
    @Pointcut("execution(* com.toleey.spring.aop.demo1.service..*.*(..))")
    public void logPointcut (){}

    //前置增强
    @Before(value = "execution(* com.toleey.spring.aop.demo1.service..*.*(..))" )
    //@Before(value = "logPointcut()")
    public void before(JoinPoint jp){
        logger.info("前置增强：调用"+jp.getTarget()+"的"+jp.getSignature().getName()+"方法,"+"参数列表"
                + Arrays.toString(jp.getArgs()));
    }

    //后置增强
    @AfterReturning(value = "execution(* com.toleey.spring.aop.demo1.service..*.*(..))" ,returning = "result")
    public void afterReturning(JoinPoint jp,Object result){
        logger.info("后置增强：调用"+jp.getTarget()+"的"+jp.getSignature().getName()+"方法，"+"返回值:"+result);
    }

    //异常抛出增强 value也行，自己测的
    @AfterThrowing(value = "execution(* com.toleey.spring.aop.demo1.service..*.*(..))" , throwing = "e")
    public void afterThrowing(JoinPoint jp,RuntimeException e) {
        logger.info("异常抛出增强："+jp.getTarget()+" 类的方法名："+jp.getSignature().getName()+"，参数为："
                +Arrays.toString(jp.getArgs()) +"的方法，出现了异常。异常信息突下："+e);
    }
    //最终增强
    @After(value = "execution(* com.toleey.spring.aop.demo1.service..*.*(..))")
    public void after(JoinPoint jp){
        //logger.info("最终增强：调用" + jp.getSignature().getName() + "方法结束了");
        logger.info("最终增强：为"+ jp.getTarget()+ "的" + jp.getSignature()+ "方法最终增强日志功能,方法的参数为"
                + Arrays.toString(jp.getArgs()) + "。方法执行结束了");
    }
    //环绕增强（增强功能包含前面四个）
    @Around(value = "execution(* com.toleey.spring.aop.demo1.service..*.*(..))")
    public Object around(ProceedingJoinPoint jp) throws Throwable{
        //		logger.info("环绕增强：调用" + jp.getTarget() + "的" + jp.getSignature().getName() +
        //				"方法,方法参数: " + Arrays.toString(jp.getArgs()));

        //前置增强信息
        logger.info("环绕增强： 前置增强：调用"+jp.getTarget()+"的"+jp.getSignature()+"方法,参数为"
                + Arrays.toString(jp.getArgs()));
        //定义返回值,通过切入点的proceed方法获得切入点的方法的返回值
        Object result = null;
        try {
            result = jp.proceed();

//			2.还可以写到里面，有异常了，try里面的就不执行了。就过去了
//			//后置增强信息
//			logger.info("环绕增强： 后置增强：调用"+jp.getTarget()+"的"+jp.getSignature()+"方法,参数为"
//					+Arrays.toString(jp.getArgs())+"返回值为"+result);

        } catch (Throwable e) {
            //异常增强代码
            logger.info("环绕增强： 异常增强：调用"+jp.getTarget()+"的"+jp.getSignature()+"方法,参数为"
                    +Arrays.toString(jp.getArgs())+"异常信息"+e);
            throw  e;//加上这个还有上面的throws Throwable。这样出现异常，下面代码就不执行了
        }finally{
            //最终增强
            logger.info("环绕增强： 最终增强：调用"+ jp.getTarget()+ "的" + jp.getSignature()+ "方法最终增强日志功能,方法的参数为"
                    + Arrays.toString(jp.getArgs()) + "。方法执行结束了");
        }
        //后置增强信息
        logger.info("环绕增强： 后置增强：调用"+jp.getTarget()+"的"+jp.getSignature()+"方法，,参数为"+Arrays.toString(jp.getArgs())
                +"返回值为"+result);
        return result;

    }
}
