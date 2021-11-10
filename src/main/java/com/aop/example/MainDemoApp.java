package com.aop.example;

import com.aop.example.dao.AccountDAO;
import com.aop.example.dao.MembershipDAO;
import com.aop.example.service.TrafficFortuneService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.logging.Logger;

public class MainDemoApp {

    private static Logger logger = Logger.getLogger(MainDemoApp.class.getName());

    public static void main(String[] args) {

        // read spring config java class
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);

        // get the bean from spring container
        AccountDAO accountDAO = context.getBean("accountDAO", AccountDAO.class);

        MembershipDAO membershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

        TrafficFortuneService service = context.getBean("trafficFortuneService", TrafficFortuneService.class);


        logger.info("\nMain Program: AroundDemoApp ");

        logger.info("calling getFortune");

        String fortune = service.getFortune(true);
        logger.info("\nMy fortune is: " + fortune);
        logger.info("Finished");


//        try {
//            // add a boolean flag to simulate exception
//            boolean tripWire = false;
//            List<Account> accounts = accountDAO.findAccounts(tripWire);
//            System.out.println("\n\nMain Program: AfterReturningDemoApp");
//            System.out.println("------");
//
//            System.out.println(accounts);
//
//            System.out.println("\n");
//        } catch (Exception e) {
//            System.out.println(e);
//        }


//        //call the business method
//        Account account = new Account();
//        account.setName("Eu");
//        account.setLevel("Gold");
//
//
//        accountDAO.addAccount(account, true);
//
//        //call the accountDao getter/setter methods
//        accountDAO.setName("foobar");
//        accountDAO.setServiceCode("silver");
//
//        String name = accountDAO.getName();
//        String code = accountDAO.getServiceCode();
//
//        //call membership
//        System.out.println(membershipDAO.addAccountMember());
//
//        membershipDAO.lala();


        //close context
        context.close();
    }
}
