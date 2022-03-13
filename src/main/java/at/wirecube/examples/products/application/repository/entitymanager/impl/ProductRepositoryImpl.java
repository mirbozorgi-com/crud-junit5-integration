package at.wirecube.examples.products.application.repository.entitymanager.impl;

import at.wirecube.examples.products.application.entity.Product;
import at.wirecube.examples.products.application.repository.entitymanager.CustomRepository;
import at.wirecube.examples.products.application.repository.entitymanager.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Profile("entitymanager")
public class ProductRepositoryImpl extends CustomRepository implements ProductRepository {


  @Override
  public Optional<Product> add(Product product) {
    return Optional.ofNullable(save(Product.class, product));
  }

  @Override
  public void update(int id, String name, double price, String description, String vat) {
    entityManager.createQuery(
            "update Product set "
                + " name= :name, "
                + " price= :price, "
                + " description= :description, "
                + " vat= :vat "
                + " Where id = :id")
        .setParameter("name", name)
        .setParameter("price", price)
        .setParameter("description", description)
        .setParameter("vat", vat)
        .setParameter("id", id)
        .executeUpdate();
  }

  @Override
  public Optional<Product> get(int productId) {
    return Optional.ofNullable(findById(Product.class, productId));
  }

  @Override
  public void delete(Product product) {
    delete(Product.class, product);
  }

  @Override
  public List<Product> getAll(String name,
      Double minPrice,
      Double maxPrice,
      String vat) {
    List<Product> products = listQueryWrapper(
        entityManager
            .createQuery("select s from Product s where s.name like CONCAT('%',:name ,'%')"
                    + " and s.vat = :vat "
                    + " and s.price BETWEEN :minPrice AND :maxPrice",
                Product.class)
            .setParameter("name", name)
            .setParameter("minPrice", minPrice)
            .setParameter("maxPrice", maxPrice)
            .setParameter("vat", vat)
    );
    return products == null ? new ArrayList<>() : products;

  }
}
