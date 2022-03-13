package at.wirecube.examples.products.application.integration.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import at.wirecube.examples.products.application.entity.Product;
import at.wirecube.examples.products.application.integration.controller.util.HeaderConfig;
import java.net.Socket;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@DisplayName("ProductControllerIntegrationTest")
@Tag("integration")
public class ProductControllerTest {

  private boolean customCondition() {
    try {
      Socket s = new Socket("127.0.0.1", 8080);
      return false;
    } catch (Exception ignored) {
    }
    return true;
  }

  @DisabledIf("customCondition")
  @DisplayName("updateIntegrationTest")
  @Test
  public void updateIntegrate() {

    Product requestModel = Product.builder()
        .id(1)
        .name("sample1")
        .price(10.02)
        .description("Hi")
        .vat("18")
        .build();
    TestRestTemplate testRestTemplate = new TestRestTemplate(
        "username",
        "password_arsalan");
    ResponseEntity<String> response = testRestTemplate.exchange(
        "http://127.0.0.1:8080/product/update", HttpMethod.PUT,
        new HttpEntity<>(requestModel, HeaderConfig.getHeaders()), String.class);
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }


}
