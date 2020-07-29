package idv.cnfang.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import idv.cnfang.exception.NotFoundException;
import idv.cnfang.model.Customer;
import idv.cnfang.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    
    private static final Customer customer = new Customer("mockuser", "mock@mail.com");
    private static final Optional<Customer> optional = Optional.of(customer);
    static {
        customer.setId((long) 10);
    }
    
    @Mock
    private CustomerRepository customerRepository;
    
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    public void whenValidId_thenReturnCustomer() {
        Mockito.when(customerRepository.findById(customer.getId()))
                .thenReturn(optional);
        
        Customer found = customerService.findById(customer.getId());
        assertThat(found).isEqualTo(customer);
    }
    
    @Test
    public void whenNonValidId_thenThrowNotFoundException() {
        Mockito.when(customerRepository.findById(customer.getId()))
                .thenReturn(Optional.empty());
        
        assertThatExceptionOfType(NotFoundException.class)
            .isThrownBy(()->customerService.findById(customer.getId()))
            .withMessage("Resource Not Found - Customer with id " + customer.getId());
        
        assertThatThrownBy(()->customerService.findById(customer.getId()))
            .isInstanceOf(NotFoundException.class)
            .hasMessageContaining("Customer with id " + customer.getId());
    }

}
