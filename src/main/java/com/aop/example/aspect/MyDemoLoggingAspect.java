package com.aop.example.aspect;

import com.aop.example.entity.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    //   this is where add all of our related advices for logging

    // let'start with an @Before advice

//    @Before("execution(public void updateAccount())")
    //when we put a full qualified name (full path to the method) the advice will be triggered just on this method
//    @Before("execution(public void com.aop.example.dao.AccountDAO.addAccount())")

    //all methods that starts with add will be called by the advice
//    @Before("execution(public void add*())")

    //in this case will be called any method that starts with add and has any return type
//    @Before("execution(* add*())")

    //() - matches a method with no arguments
    //(*) - maatches a method with one argument of any type
    //(..) -matches the method with one or more of any arguments
    //these are wild cards

    //in here will be called all methods that starts with add and has as a parameter Account type (with fully qualified name)
//    @Before("execution(* add*(com.aop.example.entity.Account))")


//    @Before("execution(* add*(..))")

//    So in our case, the @After and @AfterThrowing are in the same aspect class:
//    MyDemoLoggingAspect.java, hence in latest Spring 5.2.7, the @After will run AFTER the @AfterThrowing.

    //After advice will execute the methods regardless of it will be successful or failure
    @After("execution(* com.aop.example.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint) {

        Signature signature = joinPoint.getSignature();
        System.out.println(signature.toShortString());
        System.out.println("\n ======>>>> Executing after advice");
    }


//    @Before("com.aop.example.aspect.AOPExpresionsforDaoPackageNoSetterGetter()")
//    public void beforeAddAccountAdvice(JoinPoint joinPoint) {
//        System.out.println("\n=======>>> Executing @Before advice on addAccount()");
//
//        //display the method signature
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//
//        System.out.println("Method signature: " + signature);
//
//        //display the method arguments
//        Object[] args = joinPoint.getArgs();
//
//        for (Object arg : args) {
//            System.out.println(arg);
//
//            if (arg instanceof Account) {
//                //downcast and print Account specific stuff
//
//                Account account = (Account) arg;
//                System.out.println("account name: " + account.getName());
//                System.out.println("account code: " + account.getLevel());
//            }
//        }
//    }

    @AfterThrowing(pointcut = "execution(* com.aop.example.dao.AccountDAO.findAccounts(..))",
                    throwing = "exception")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exception) {
        System.out.println("\n ======>>>> Executing afterThrowing");

        System.out.println(exception);
    }

    @AfterReturning(pointcut = "execution(* com.aop.example.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result) {

        //print out witch method we are advising on
        String string = joinPoint.getSignature().toShortString();
        System.out.println("\n=====>>>> Executing AfterReturning on method: " + string);

        //print out the results on method result
        System.out.println("\n=====>>>> result is : " + result);

        //modify "result" list: add, remove, update etc..

        if(!result.isEmpty()) {
            result.add(new Account("Jora", "Aur"));
        }

        //post-process the data
//        convertAccountNamesToUpperCase(result);

        System.out.println("\n=========> result of post-processing: " + result);
    }

    private void convertAccountNameclasssToUpperCase(List<Account> result) {

        result.forEach(account -> {
            String upperCaseName = account.getName().toUpperCase();
            account.setName(upperCaseName);
        });
    }
}
