package at.wirecube.examples.products.application.unit.controller;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import at.wirecube.examples.products.application.entity.Product;
import at.wirecube.examples.products.application.repository.jpa.ProductRepository;
import at.wirecube.examples.products.application.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@DisplayName("ProductControllerTest")
@WebMvcTest
public class ProductControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProductService productService;

  @MockBean
  private ProductRepository productRepository;

  private static final ObjectMapper mapper = new ObjectMapper();


  @DisplayName("update")
  @Test
  public void update() throws Exception {
    Product requestModel = Product.builder()
        .id(1)
        .name("sample1")
        .price(10.02)
        .description("Hi")
        .vat("18").build();

    when(productService.update(
        requestModel.getId(),
        requestModel.getName(),
        requestModel.getPrice(),
        requestModel.getDescription(),
        requestModel.getVat())).thenReturn(requestModel);
    String json = mapper.writeValueAsString(requestModel);
    mockMvc.perform(
            put("/product/update")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)
                .with(httpBasic("username", "password_arsalan")))
        .andExpect(status().isOk());
  }

}
