package idv.cnfang.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import idv.cnfang.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
    
    Product findByName(String name);
}
