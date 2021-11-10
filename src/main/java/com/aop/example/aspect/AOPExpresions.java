package com.aop.example.aspect;

import com.aop.example.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

@Aspect
public class AOPExpresions {

    //point cut declaration is a declaration of the advice param to use it on multiple advice methods
    //once it is declared it can be updated once for all advices where is used
    @Pointcut("execution(* com.aop.example.dao.*.addAccount(..))")
    public void forDaoPackage(){}

    //create point cut for getter methods
    @Pointcut("execution(* com.aop.example.dao.*.get*(..))")
    public void forGetterMethods(){}


    //create point cut for setter methods
    @Pointcut("execution(* com.aop.example.dao.*.set*(..))")
    public void forSetterMethods(){}

    //create point: include package .. exclude getter/setter
    @Pointcut("forDaoPackage() && !(forGetterMethods() || forSetterMethods())")
    public void forDaoPackageNoSetterGetter() {}

    //add a new advice for @AfterReturning on the findAccounts method

}
