package at.wirecube.examples.products.application.unit.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import at.wirecube.examples.products.application.entity.Product;
import at.wirecube.examples.products.application.repository.jpa.ProductRepository;
import at.wirecube.examples.products.application.service.impl.ProductServiceImpl;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("ProductServiceTest")
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

  @Mock
  private ProductRepository productRepository;

  @InjectMocks
  private ProductServiceImpl productService;

  @DisplayName("update")
  @Test
  public void update() {
    Product product = Product.builder()
        .id(1)
        .name("Apple")
        .price(2.25)
        .description("tellus id nunc interdum feugiat. Sed nec")
        .vat("20")
        .build();

    Mockito.when(productRepository.findById(Mockito.any(Integer.class))).thenReturn(
        Optional.ofNullable(product));

    Mockito.when(productRepository.update(product.getName(),
        product.getPrice(),
        product.getDescription(),
        product.getVat(),
        product.getId())).thenReturn(1);

    Product productUpdated = productService.update(product.getId(),
        product.getName(),
        product.getPrice(),
        product.getDescription(),
        product.getVat());
    assertAll("Verify All Fields",
        () -> assertEquals(productUpdated.getName(), product.getName()),
        () -> assertEquals(productUpdated.getPrice(), product.getPrice()),
        () -> assertEquals(productUpdated.getDescription(), product.getDescription()),
        () -> assertEquals(productUpdated.getVat(), product.getVat())
    );
  }
}
