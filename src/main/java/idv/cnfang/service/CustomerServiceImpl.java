package idv.cnfang.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.cnfang.exception.NotFoundException;
import idv.cnfang.exception.DuplicateResourceException;
import idv.cnfang.model.Customer;
import idv.cnfang.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public Customer findById(Long customerId) {
        Optional<Customer> optional = customerRepository.findById(customerId);
        return optional.orElseThrow(()-> new NotFoundException("Customer with id " + customerId));
    }

    @Override
    public Customer findByUsername(String username) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null)
            throw new NotFoundException("Customer with username " + username);
        return customer;
    }
    
    @Override
    public Customer findByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer == null)
            throw new NotFoundException("Customer with email " + email);
        return customer;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if (customerExist(customer))
            throw new DuplicateResourceException("Customer with username/email " + customer.getUsername() + "/" + customer.getEmail());
        return customerRepository.save(customer);
    }

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public void deleteAllCustomers() {
        customerRepository.deleteAll();
    }

    @Override
    public List<Customer> findAllCustomers() {
        return (List<Customer>) customerRepository.findAll();
    }

    @Override
    public boolean customerExist(Customer customer) {
        return customerRepository.findByUsername(customer.getUsername()) != null || customerRepository.findByEmail(customer.getEmail()) != null;
    }

}
