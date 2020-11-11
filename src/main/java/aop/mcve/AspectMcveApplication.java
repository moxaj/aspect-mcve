package aop.mcve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class AspectMcveApplication {
  public static void main(String[] args) {
    SpringApplication.run(AspectMcveApplication.class, args);
  }
}
