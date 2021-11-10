package com.aop.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Aspect
public class AroundAdviceDemo {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Around("execution(* com.aop.example.service.*.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        Object result = null;
        //print out method we are advising on
        String signature = proceedingJoinPoint.getSignature().toShortString();
        logger.info("\n======> Executing @Around on method: " + signature);

        //get begin timestamp
        long begin = System.currentTimeMillis();

        //now, let's execute method
        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            //log exception
            logger.warning("@Around advice: We have a problem " + e);
//            result = "Nothing exciting here. Move along!";
            throw e;
        }


        //get end timestamp
        long end = System.currentTimeMillis();

        //compute duration and siplay it
        long duration = end - begin;
        logger.info("\n====> Duration: " + duration/1000.0 + " seconds");

        return result;
    }
}
