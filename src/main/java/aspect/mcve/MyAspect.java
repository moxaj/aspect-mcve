package aspect.mcve;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
  private final MyService myService;

  public MyAspect(MyService myService) {
    this.myService = myService;
  }

  @Pointcut("within(aspect.mcve..*)")
  public void inDomain() {

  }

  @Pointcut("@target(org.springframework.stereotype.Service)")
  public void inService() {

  }

  @Pointcut("execution(* aspect.mcve.MyService.*(..))")
  public void inMyService() {

  }

  @Pointcut("@target(org.springframework.web.bind.annotation.RestController)")
  public void inController() {

  }

  @Around("inDomain() && inController()")
  public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {
    joinPoint.proceed();                                   // Does not print the message below
    myService.foo();                                       // Does not print the message below
    return myService.aroundController(joinPoint::proceed); // Prints the message below
  }

  @Around("inDomain() && inService() && !inMyService()")
  public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable {
    System.out.println("You should never see this message!");
    return joinPoint.proceed();
  }

  public interface Callable<T> {
    T call() throws Throwable;
  }
}
