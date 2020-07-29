package idv.cnfang.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.cnfang.exception.NotFoundException;
import idv.cnfang.model.Product;
import idv.cnfang.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired 
    private ProductRepository productRepository;
    
    
    @Override
    public Product findByName(String name) {
        Product product = productRepository.findByName(name);
        if (product == null)
            throw new NotFoundException("Product with name " + name);
        return product;
    }

    @Override
    public List<Product> findAllProducts() {
        return (List<Product>) productRepository.findAll();
    }

    
    @Override
    public Product findById(Long productId) {
        return productRepository.findById(productId)
                                .orElseThrow(() -> new NotFoundException("Product with id " + productId));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

}
