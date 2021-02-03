package idv.cnfang.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import javax.sql.DataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import idv.cnfang.model.Product;


@DataJpaTest
class ProductRepositoryTest {
	
	@Value("${myapp.describe}")
	private String myString;
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired 
    private DataSource dataSource;
    
    @Autowired
    Environment environment;

    @Test
    void whenFindByName_thenReturnProduct() {
        Product product = new Product(1000.0, "Mocking Product Title", "Mocking product description", "mocking path");
        entityManager.persistAndFlush(product);
       
        Product found = productRepository.findByName(product.getName());
        assertThat(found).isEqualTo(product);
    }
    
    @Test
    void injectedComponentsAreNotNull(){
      assertThat(dataSource).isNotNull();
      assertThat(entityManager).isNotNull();
      assertThat(productRepository).isNotNull();
      assertThat(myString).isEqualTo("hello.world");
    }

}
