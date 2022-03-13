package at.wirecube.examples.products.application.repository.jpa;

import at.wirecube.examples.products.application.entity.Product;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Profile("jpa")
public interface ProductRepository extends JpaRepository<Product, Integer>,
    JpaSpecificationExecutor<Product> {

  void deleteById(int id);

  void deleteByName(String name);

  Product findByName(String name);

  @Modifying
  @Query("update Product p set p.name = ?1, p.price = ?2 ,p.description=?3 , p.vat=?4 where p.id = ?5")
  int update(String name, double price, String description, String vat, int id);

  @Query("select s from Product s where s.id=1")
  Product health();
}
