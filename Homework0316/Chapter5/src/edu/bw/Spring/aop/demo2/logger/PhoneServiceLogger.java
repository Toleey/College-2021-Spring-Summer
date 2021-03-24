package edu.bw.Spring.aop.demo2.logger;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

import java.util.Arrays;

public class PhoneServiceLogger {

    private static Logger logger = Logger.getLogger(PhoneServiceLogger.class);

    public void before(JoinPoint jp){
        logger.info(" 调用了 "+jp.getTarget()+" 的 "+jp.getSignature().getName()+" 方法。方法入参 "+
                Arrays.toString(jp.getArgs()));
    }
    public void afterReturning(JoinPoint jp,Object result){
        logger.info(" 调用了 "+jp.getTarget()+" 的 "+jp.getSignature().getName()+" 方法，返回值是："+result);
    }
}
