package idv.cnfang.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import idv.cnfang.model.Customer;
import idv.cnfang.service.CustomerService;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    private static final Customer customer = new Customer("orange", "ohome@mail.com");
    static {
        customer.setId((long) 523);
    }
    
    @InjectMocks
    private CustomerController customerController;
    
    @Mock
    private CustomerService customerService;

    @Test
    public void getCustomer_returnReponseOk_validCustomerId() {
        Mockito.when(customerService.findById(customer.getId())).thenReturn(customer);
        ResponseEntity<?> response = customerController.getCustomer(customer.getId());
    }

}
