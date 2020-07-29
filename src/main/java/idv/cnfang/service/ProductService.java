package idv.cnfang.service;

import java.util.List;

import idv.cnfang.model.Product;


public interface ProductService {
    
    Product findByName(String name);
    
    List<Product> findAllProducts();

    Product findById(Long productId);
    
    Product createProduct(Product product);
}
