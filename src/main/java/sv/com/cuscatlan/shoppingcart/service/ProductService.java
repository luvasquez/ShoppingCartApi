package sv.com.cuscatlan.shoppingcart.service;

import sv.com.cuscatlan.shoppingcart.controller.response.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> findAll();

    ProductResponse findById(final Integer id);
}
