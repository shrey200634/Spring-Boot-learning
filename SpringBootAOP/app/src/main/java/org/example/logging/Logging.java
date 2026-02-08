package org.example.logging;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component; // Don't forget this!

@Aspect
@Component // You likely need this for Spring to find this class
public class Logging {

    // CHANGE 'springaoptut' TO 'org.example'
    @Before("execution(public void org.example.service.UserService.logIn())")
    public void logginAdvice1() {
        System.out.println("before advice for login is execute ");
    }

    // CHANGE 'springaoptut' TO 'org.example'
    @After("execution(public void org.example.service.UserService.logIn())")
    public void logginAdvice2() {
        System.out.println("after advice for login is execxute ");
    }

    @Around("execution(public void org.example.service.UserService.lonIn())")
    public void loggingAdvice3(){
        System.out.println("before and after invoking method login");
    }

    @AfterThrowing("execution(public void org.example.service.UserService.logIn())")
    public void loggingAdvice4(){
        System.out.println("Exception thrown in logout method" );

    }
    @AfterReturning("execution(public void org.example.service.UserService.logIn())")
    public void loggingAdvice5() {
        System.out.println("AfterReturning advice for logOut is run");
    }

    @Pointcut("execution(public * springbootaoptut.service.UserService.*(..))")
    public void pointCut() {
    }
    @Pointcut("execution(public * springbootaoptut.service.UserService.*(..))")
    public void pointCut1() {
    }

    @Before("pointCut() || pointCut1()")
    public void loggingAdvice6() {
        System.out.println("Before advice using pointcut is executed");
    }

}