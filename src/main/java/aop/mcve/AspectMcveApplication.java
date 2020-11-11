package aop.mcve;

import org.aspectj.lang.Aspects;
import org.aspectj.weaver.loadtime.Agent;
import org.springframework.aop.framework.Advised;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AspectMcveApplication {
  public static void main(String[] args) throws Throwable {
    ConfigurableApplicationContext context = SpringApplication.run(AspectMcveApplication.class, args);
    MyAspect myAspectBean = context.getBean(MyAspect.class);
    System.out.println(myAspectBean + " / " + (myAspectBean instanceof Advised));
    boolean ltwActive = false;
    try {
      Agent.getInstrumentation();
      ltwActive = true;
    }
    catch (UnsupportedOperationException ignored) {}
    if (ltwActive)
      Aspects.aspectOf(MyAspect.class).setMyService(context.getBean(MyService.class));
    else
      myAspectBean.setMyService(context.getBean(MyService.class));
    context.getBean(MyService.class).aroundController(() -> {
      System.out.println("hello");
      return null;
    });
    context.getBean(MyController.class).controllerMethod();
  }
}
