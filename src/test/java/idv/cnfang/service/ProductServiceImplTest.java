package idv.cnfang.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import idv.cnfang.model.Product;
import idv.cnfang.repository.ProductRepository;

@ExtendWith(SpringExtension.class)
@Import(ProductServiceImpl.class)
public class ProductServiceImplTest {
    
    private static final Product product = new Product(100.0, "Dance", "Fake Description", "path/to/product");
    static {
        product.setId((long) 50);
    }

    @Autowired
    ProductService productService;
    
    @MockBean
    ProductRepository productRepostory;

    @Test
    public void findByName_returnProduct_ProductExist() {
        Mockito.when(productRepostory.findByName(product.getName()))
            .thenReturn(product);
        
        Product found = productService.findByName(product.getName());
        assertThat(found.getName())
            .isEqualTo(product.getName());
    }
    

}
