package at.wirecube.examples.products.application.model;

import at.wirecube.examples.products.application.enums.VatType;
import at.wirecube.examples.products.application.exception.VatEnumException;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductSaveModel {

  @NotNull
  private String name;
  @NotNull
  private Double price;
  private String description;

  private String vat;

  public String getVat() {
    int intValueOfVat = 0;
    try {
      intValueOfVat = Integer.parseInt(vat);
    } catch (Exception e) {
      throw new VatEnumException();
    }
    if (vat == null || VatType.valueOf(intValueOfVat) == null) {
      throw new VatEnumException();
    }
    return vat;
  }
}

