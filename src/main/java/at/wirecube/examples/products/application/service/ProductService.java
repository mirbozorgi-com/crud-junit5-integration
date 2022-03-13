package at.wirecube.examples.products.application.service;

import at.wirecube.examples.products.application.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

  Product add(String name, double price, String description, String vat);

  Product get(int id);

  void delete(int id);

  Product update(int id, String name, double price, String description, String vat);

  Page<Product> getAll(String name,
      Double minPrice,
      Double maxPrice,
      String vat,
      Pageable pageable);


}

