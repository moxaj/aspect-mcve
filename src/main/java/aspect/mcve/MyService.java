package aspect.mcve;

import org.springframework.stereotype.Service;

@Service
public class MyService {
  public Object aroundController(MyAspect.Callable<?> callable) throws Throwable {
    return callable.call();
  }

  public void foo() {

  }
}
