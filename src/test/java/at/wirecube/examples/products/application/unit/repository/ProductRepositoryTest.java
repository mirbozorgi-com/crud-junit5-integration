package at.wirecube.examples.products.application.unit.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import at.wirecube.examples.products.application.entity.Product;
import at.wirecube.examples.products.application.repository.jpa.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ProductRepositoryTest")
@DataJpaTest
public class ProductRepositoryTest {

  @Autowired
  private ProductRepository repository;


  @DisplayName("save")
  @Test
  @Order(1)
  public void save() {
    Product product = Product.builder()
        .name("arsalan")
        .price(1.00)
        .description("desc")
        .vat("18")
        .build();
    Product productSaved = repository.save(product);
    assertAll("Verify All Fields",
        () -> assertEquals(productSaved.getName(), product.getName()),
        () -> assertEquals(productSaved.getPrice(), product.getPrice()),
        () -> assertEquals(productSaved.getDescription(), product.getDescription()),
        () -> assertEquals(productSaved.getVat(), product.getVat())
    );

  }

  @DisplayName("update")
  @Test
  @Order(2)
  public void update() {
    Product product = repository.findByName("Apple");
    Product productUpdated = Product.builder()
        .id(product.getId())
        .name("Apple")
        .price(2.00)
        .description("desc2")
        .vat("18")
        .id(product.getId())
        .build();
    int update = repository.update(productUpdated.getName(),
        productUpdated.getPrice(),
        productUpdated.getDescription(),
        productUpdated.getVat(),
        productUpdated.getId());

    assertEquals(update, 1);


  }


}
