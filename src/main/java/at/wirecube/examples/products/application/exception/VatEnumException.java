package at.wirecube.examples.products.application.exception;

import at.wirecube.examples.products.application.enums.VatType;
import org.springframework.http.HttpStatus;

public class VatEnumException extends CustomException {

  public VatEnumException() {
    super("incorrect enum exception", HttpStatus.BAD_REQUEST);
    this.setData("correct values are one of the string value of: " + VatType.valueValidInfo());
  }
}
