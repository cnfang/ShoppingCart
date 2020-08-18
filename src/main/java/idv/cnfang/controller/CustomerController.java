package idv.cnfang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import idv.cnfang.model.Customer;
import idv.cnfang.service.CustomerService;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    protected static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    
    @Autowired
    CustomerService customerService;
    
    // -------------------Retrieve All Customers----------------------------------------
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> listAllUsers() {
        logger.info("Fetching All Customers");
        
        List<Customer> customer = customerService.findAllCustomers();
        return customer.isEmpty()? ResponseEntity.noContent().build(): ResponseEntity.ok(customer);
    }

    
    // -------------------Retrieve Single Customer by Id---------------------------------
    @RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomer(@PathVariable("customerId") long customerId) {
        logger.info("Fetching Customer with id {}", customerId);
        
        Customer customer = customerService.findById(customerId);
        return ResponseEntity.ok(customer);
    }
    
    
    // ------------Retrieve Single Customer by username---------------------------------
    @RequestMapping(value = "/username={username}", method = RequestMethod.GET)
    public ResponseEntity<?> getCustomerByUsername(@PathVariable("username") String username) {
        logger.info("Fetching Customer with username {}", username);
        
        Customer customer = customerService.findByUsername(username);
        return ResponseEntity.ok(customer);
    }

    
    // -------------------Create a Customer-------------------------------------------
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        logger.info("Creating Customer : {}", customer);
        
        Customer currentCustomer = customerService.createCustomer(customer);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getCustomer(currentCustomer.getId()));
        return ResponseEntity.created(linkTo.toUri()).body(currentCustomer);
    }

    
    // -------------------Update a Customer------------------------------------------
    @RequestMapping(value = "/{customerId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCustomer(@PathVariable("customerId") long customerId, @RequestBody Customer customer) {
        logger.info("Updating customer with id {}", customerId);

        Customer currentCustomer = customerService.findById(customerId);
        currentCustomer.setUsername(customer.getUsername());
        currentCustomer.setEmail(customer.getEmail());
        customerService.updateCustomer(currentCustomer);
        return ResponseEntity.ok(currentCustomer);
    }

    
    // ------------------- Delete a Customer-----------------------------------------
    @RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCustomer(@PathVariable("customerId") long customerId) {
        logger.info("Fetching & Deleting customer with id {}", customerId);

        customerService.findById(customerId);
        customerService.deleteCustomerById(customerId);
        return ResponseEntity.noContent().build();
    }

    
    // --------------------------- Delete All Customers-----------------------------
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAllCustomers() {
        logger.info("Deleting All Customers");

        customerService.deleteAllCustomers();
        return ResponseEntity.noContent().build();
    }
}
