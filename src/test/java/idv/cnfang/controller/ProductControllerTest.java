package idv.cnfang.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import idv.cnfang.model.Product;
import idv.cnfang.service.ProductService;
import idv.cnfang.service.ProductServiceImpl;

@WebMvcTest(ProductController.class)
@AutoConfigureJsonTesters
@Import({ProductServiceImpl.class})
class ProductControllerTest {
    
    private static final Product product = new Product(100.0, "Dance", "Fake Description", "path/to/product");
    static {
        product.setId((long) 50);
    }
    
    @Autowired
    private JacksonTester<Product> jsonMapper;
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private ProductService productService;
    
    @Test
    public void getProduct_returnResponseOk_ValidProductId() throws Exception {
        given(productService.findById(product.getId())).willReturn(product);
        
        MockHttpServletResponse response = mvc.perform(get("/api/product/50"))
                                              .andReturn()
                                              .getResponse();
        // check status
        assertThat(response.getStatus())
            .isEqualTo(HttpStatus.OK.value());
        
        // check content type
        assertThat(response.getContentType())
            .isEqualTo(MediaType.APPLICATION_JSON.toString());
        
        // check content body
        assertThat(response.getContentAsString())
            .isEqualTo(jsonMapper.write(product).getJson());
    }

}
