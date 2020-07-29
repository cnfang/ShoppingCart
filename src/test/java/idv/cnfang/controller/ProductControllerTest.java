package idv.cnfang.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import idv.cnfang.model.Product;
import idv.cnfang.service.ProductService;
import idv.cnfang.service.ProductServiceImpl;

@WebMvcTest(ProductController.class)
@Import({ProductServiceImpl.class, ObjectMapper.class})
class ProductControllerTest {
    
    private static final Product product = new Product(100.0, "Dance", "Fake Description", "path/to/product");
    static {
        product.setId((long) 50);
    }
    
    // for mapping object to json
    @Autowired
    private ObjectMapper mapper;
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private ProductService productService;
    
    @Test
    public void getProduct_returnResponseOk_ValidProductId() throws Exception {
        Mockito.when(productService.findById(product.getId())).thenReturn(product);
        
        mvc.perform(get("/api/product/50"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().json(mapper.writeValueAsString(product)));
    }

}
