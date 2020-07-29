package idv.cnfang.service;

import java.util.List;

import idv.cnfang.model.Customer;


public interface CustomerService {
    Customer findById(Long customerId);
    
    Customer findByEmail(String email);
    
    Customer findByUsername(String username);
    
    Customer createCustomer(Customer customer);

    void saveCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomerById(Long customerId);

    void deleteAllCustomers();

    List<Customer> findAllCustomers();

    boolean customerExist(Customer customer);
}
