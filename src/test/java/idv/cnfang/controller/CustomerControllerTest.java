package idv.cnfang.controller;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.JsonPathResultMatchers.*;

import idv.cnfang.model.Customer;
import idv.cnfang.service.CustomerService;
import idv.cnfang.service.CustomerServiceImpl;

@WebMvcTest(CustomerController.class)
@Import({CustomerServiceImpl.class, ObjectMapper.class})
public class CustomerControllerTest {

    private static final Customer customer = new Customer("orange", "ohome@mail.com");
    static {
        customer.setId((long) 523);
    }
    @Autowired
    private ObjectMapper mapper;
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private CustomerService customerService;

    @Test
    public void getCustomer_returnReponseOk_validCustomerId() throws Exception {
        given(customerService.findById(customer.getId())).willReturn(customer);
        // Mockito.when(customerService.findById(customer.getId()).thenReturn(customer);
        
        mvc.perform(get("/api/customer/523"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(mapper.writeValueAsString(customer)));
    }

}
