package at.wirecube.examples.products.application.controller;

import at.wirecube.examples.products.application.entity.Product;
import at.wirecube.examples.products.application.repository.jpa.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
@RequiredArgsConstructor
public class HealthController {

  private final ProductRepository repository;

  @RequestMapping(value = "/health", method = RequestMethod.GET, produces = "text/plain")
  public String monitor() {
    StringBuilder builder = new StringBuilder();
    int db_check = 0;
    int app_check = 1;
    try {
      Product health = repository.health();
      if (health != null) {
        db_check = 1;
      }
    } catch (Exception ignored) {
    }
    String result =
        String.format(
            "db_check %d \n" +
                "app_check %d \n",
            db_check,
            app_check);

    builder.append(result);

    return builder.toString();
  }

}
