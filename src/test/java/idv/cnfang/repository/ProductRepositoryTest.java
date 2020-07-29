package idv.cnfang.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.core.env.Environment;

import idv.cnfang.model.Product;

@DataJpaTest
class ProductRepositoryTest {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    Environment environment;

    @Test
    void whenFindByName_thenReturnProduct() {
        Product product = new Product(1000.0, "Mocking Product Title", "Mocking product description", "mocking path");
        entityManager.persistAndFlush(product);
       
        Product found = productRepository.findByName(product.getName());
        assertThat(found).isEqualTo(product);
    }

}
