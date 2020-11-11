package aop.mcve;

import org.springframework.stereotype.Service;

@Service
public class MyService {
  public interface Callable<T> {
    T call() throws Throwable;
  }

  public Object aroundController(Callable<?> callable) throws Throwable {
    return callable.call();
  }

  public void serviceMethod() {}
}
