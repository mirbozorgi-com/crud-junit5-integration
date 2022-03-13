package at.wirecube.examples.products.application.model;

import at.wirecube.examples.products.application.enums.VatType;
import at.wirecube.examples.products.application.exception.VatEnumException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductGetModel {

  private String name;
  private Double price;
  private String description;
  private String vat;

  public String getVat() {
    if (vat != null && VatType.valueOf(Integer.parseInt(vat)) == null) {
      throw new VatEnumException();
    }
    return vat;
  }

}
