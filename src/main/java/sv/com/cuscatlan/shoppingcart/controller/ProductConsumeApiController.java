package sv.com.cuscatlan.shoppingcart.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sv.com.cuscatlan.shoppingcart.controller.response.ProductResponse;
import sv.com.cuscatlan.shoppingcart.service.ProductService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor()
public class ProductConsumeApiController {

    @NonNull
    private final ProductService productService;

    @GetMapping
    @ResponseStatus(OK)
    public List<ProductResponse> getProductList() {
        return productService.findAll();
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(OK)
    public ProductResponse getProductById(@PathVariable Integer id) {
        return productService.findById(id);
    }
}
