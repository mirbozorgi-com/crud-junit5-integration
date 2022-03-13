package at.wirecube.examples.products.application.controller;

import at.wirecube.examples.products.application.entity.Product;
import at.wirecube.examples.products.application.model.ProductDeleteModel;
import at.wirecube.examples.products.application.model.ProductGetModel;
import at.wirecube.examples.products.application.model.ProductSaveModel;
import at.wirecube.examples.products.application.model.ProductUpdateModel;
import at.wirecube.examples.products.application.service.ProductService;
import at.wirecube.examples.products.application.util.helper.ResponseHelper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService service;

  @ApiOperation(value = "This Api is for creating new Product", response = Product.class)
  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public ResponseEntity add(@RequestBody @Validated ProductSaveModel model) {
    return ResponseHelper.response(service.add(model.getName(),
        model.getPrice(),
        model.getDescription(),
        model.getVat()
    ));
  }

  @ApiOperation(
      value = "This Api is for getting Product by their id",
      response = Product.class)
  @RequestMapping(value = "/get", method = RequestMethod.GET)
  public ResponseEntity get(@RequestParam int id) {
    return ResponseHelper.response(service.get(id));
  }

  @ApiOperation(
      value = "This Api is for getting Products with filtering",
      response = Product.class, responseContainer = "List")
  @RequestMapping(value = "/get-all", method = RequestMethod.GET)
  public ResponseEntity getAll(@Validated ProductGetModel model, Pageable pageable) {
    return ResponseHelper.response(
        service.getAll(model.getName(), model.getPrice(), model.getPrice(), model.getVat(),
            pageable));
  }

  @ApiOperation(value = "This Api is for deleting Product with their id")
  @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
  public ResponseEntity delete(@RequestBody @Validated ProductDeleteModel model) {
    service.delete(model.getId());
    return ResponseHelper.response(true);
  }

  @ApiOperation(value = "This Api is for updating Product")
  @RequestMapping(value = "/update", method = RequestMethod.PUT)
  public ResponseEntity update(@RequestBody @Validated ProductUpdateModel model) {
    return ResponseHelper.response(service.update(
        model.getId(),
        model.getName(),
        model.getPrice(),
        model.getDescription(),
        model.getVat()));
  }
}
