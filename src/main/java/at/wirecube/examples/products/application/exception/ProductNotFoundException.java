package at.wirecube.examples.products.application.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends CustomException {

  public ProductNotFoundException() {
    super("not_found", HttpStatus.NOT_FOUND);
    this.setData("product_not_found");
  }
}
