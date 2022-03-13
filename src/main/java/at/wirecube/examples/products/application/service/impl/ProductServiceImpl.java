package at.wirecube.examples.products.application.service.impl;

import at.wirecube.examples.products.application.entity.Product;
import at.wirecube.examples.products.application.exception.ProductNotFoundException;
import at.wirecube.examples.products.application.repository.jpa.ProductRepository;
import at.wirecube.examples.products.application.service.ProductService;
import javax.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Profile("jpa")
public class ProductServiceImpl implements ProductService {


  private final ProductRepository repository;


  @Transactional
  @Override
  public Product add(String name, double price, String description, String vat) {
    Product product = Product.builder()
        .name(name)
        .price(price)
        .description(description)
        .vat(vat)
        .build();
    return repository.save(product);
  }

  @Override
  public Product get(int id) {
    return repository.findById(id).orElseThrow(ProductNotFoundException::new);
  }

  @Transactional
  @Override
  public void delete(int id) {
    get(id);
    repository.deleteById(id);
  }

  @Transactional
  @Override
  public Product update(int id, String name, double price, String description, String vat) {
    repository.findById(id).orElseThrow(ProductNotFoundException::new);
    repository.update(name, price, description, vat, id);
    return Product.builder()
        .id(id)
        .name(name)
        .price(price)
        .description(description)
        .vat(vat)
        .build();
  }

  @Override
  public Page<Product> getAll(String name,
      Double minPrice,
      Double maxPrice,
      String vat,
      Pageable pageable) {
    Specification<Product> specification = getSpecification(name, minPrice, maxPrice, vat);
    return repository.findAll(specification, pageable);
  }

//  ################################
//  #########PRIVATE METHODS########
//  ################################

  private Specification<Product> getSpecification(String name,
      Double minPrice,
      Double maxPrice,
      String vat) {
    return (root, criteriaQuery, criteriaBuilder) -> {
      criteriaQuery.distinct(true);
      Predicate result = criteriaBuilder.isNotNull(root.get("id"));
      if (name != null) {
        result = criteriaBuilder.and(result,
            criteriaBuilder.like(root.get("name"), "%" + name + "%"));
      }

      if (minPrice != null) {
        result = criteriaBuilder.and(result,
            criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));

      }

      if (maxPrice != null) {
        result = criteriaBuilder.and(result,
            criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));

      }

      if (vat != null) {
        result = criteriaBuilder.and(result,
            criteriaBuilder.equal(root.get("vat"), vat));
      }
      return result;

    };
  }


}
