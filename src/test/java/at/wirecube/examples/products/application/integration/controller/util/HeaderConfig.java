package at.wirecube.examples.products.application.integration.controller.util;

import org.springframework.http.HttpHeaders;

public class HeaderConfig {

  public static HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Accept", "application/json");
    headers.add("Content-type", "application/json");
    return headers;
  }

}
