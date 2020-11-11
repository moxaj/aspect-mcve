package aop.mcve;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
  private MyService myService;

  public void setMyService(MyService myService) {
    this.myService = myService;
  }

  @Pointcut("within(aop.mcve..*) && !within(MyAspect) && execution(* *(..))")
  public void inDomain() {}

  @Pointcut("@target(org.springframework.stereotype.Service)")
  public void inService() {}

  @Pointcut("within(aop.mcve.MyService)")
  public void inMyService() {}

  @Pointcut("@target(org.springframework.web.bind.annotation.RestController)")
  public void inController() {}

  @Around("inDomain() && inController()")
  public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("aroundController -> " + joinPoint);

    // Does not print the message below
    // return joinPoint.proceed();

    // Does not print the message below
    // myService.serviceMethod(); return null;

    // Prints the message below
    return myService.aroundController(() -> {
      System.out.println("Callable this = " + this);
      return joinPoint.proceed();
    });
  }

  @Around("inDomain() && inService() && !inMyService()")
  public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("aroundService -> " + joinPoint);
    System.out.println("  this = " + joinPoint.getThis());
    System.out.println("  target = " + joinPoint.getTarget());
    System.out.println("  You should never see this message!");
    return joinPoint.proceed();
  }
}
