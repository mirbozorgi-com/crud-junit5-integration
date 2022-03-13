package at.wirecube.examples.products.application.repository.entitymanager;

import at.wirecube.examples.products.application.entity.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.context.annotation.Profile;

@Profile("entitymanager")
public interface ProductRepository {

  Optional<Product> add(Product product);

  void update(int id, String name, double price, String description, String vat);

  Optional<Product> get(int productId);

  void delete(Product product);

  List<Product> getAll(String name,
      Double minPrice,
      Double maxPrice,
      String vat);


}
