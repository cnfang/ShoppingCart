package idv.cnfang.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import idv.cnfang.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{

    Customer findByUsername(String username);
    
    Customer findByEmail(String email);
}
