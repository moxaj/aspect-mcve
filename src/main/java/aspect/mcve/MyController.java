package aspect.mcve;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class MyController {
  @GetMapping("/foo")
  public void foo() {}
}
