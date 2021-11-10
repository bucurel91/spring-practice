package com.aop.example.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyPerformAnalyticsAspect {

    @Before("com.aop.example.aspect.AOPExpresions.forDaoPackageNoSetterGetter()")
    public void beforePerformAPIAnalytics() {

        System.out.println("\n=======>>> Perform API analytics");
    }
}
