package idv.cnfang.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import idv.cnfang.model.Customer;

@DataJpaTest
class CustomerRepositoryTest {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private TestEntityManager entityManager;
    
  
    @Test
    void whenFindByUsername_thenReturnCustomer() {
        Customer customer = new Customer("mockUsername", "mock@mail.com");
        entityManager.persist(customer);
        entityManager.flush();
        Customer found = customerRepository.findByUsername(customer.getUsername());
        assertThat(found).isEqualTo(customer);
    }
    
    @Test
    void whenFindByEmail_thenReturnCustomer() {
        Customer customer = new Customer("mockUsername", "mock@mail.com");
        entityManager.persistAndFlush(customer);
        Customer found = customerRepository.findByEmail(customer.getEmail());
        
        assertThat(found).isEqualTo(customer);
        List<Customer> customerList = (List<Customer>) customerRepository.findAll();
        assertThat(customerList).hasSize(1);
    }

}
