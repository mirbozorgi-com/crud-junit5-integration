package at.wirecube.examples.products.application.enums;

import java.util.HashMap;
import java.util.Map;


public enum VatType {
  TEN_PERCENT(10),
  TWELVE_PERCENT(12),
  EIGHTEEN_PERCENT(18),
  ;
  private final int value;
  private static Map map = new HashMap<>();

  VatType(int value) {
    this.value = value;
  }

  static {
    for (VatType vatType : VatType.values()) {
      map.put(vatType.value, vatType);
    }
  }

  public static VatType valueOf(int vatType) {
    return (VatType) map.get(vatType);
  }

  public int getValue() {
    return value;
  }

  public static String valueValidInfo() {
    return map.keySet().toString();
  }
}
