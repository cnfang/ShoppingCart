package idv.cnfang.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import idv.cnfang.model.Product;
import idv.cnfang.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
    protected static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    
    @Autowired
    ProductService productService;
    
    // -------------------Retrieve All Products---------------------------------------------
    @RequestMapping(value = "/product/", method = RequestMethod.GET)
    public ResponseEntity<?> listAllProducts() {
        List<Product> products = productService.findAllProducts();
        return products.isEmpty()? ResponseEntity.noContent().build(): ResponseEntity.ok(products);
    }

    
    // -------------------Retrieve Single Product By Id------------------------------------------
    @RequestMapping(value = "/product/{productId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@PathVariable("productId") long productId) {
        logger.info("Fetching Product with id {}", productId);
        
        Product product = productService.findById(productId);
        return ResponseEntity.ok(product);
    }
}
