package idv.cnfang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "username", length = 255, nullable = false)
    private String username;
    
    @Column(name = "email", length = 255, nullable = false)
    private String email;

    
    public Customer() {
    }
    

    /**
     * 
     * @param username, username
     * @param email, email
     */
    public Customer(String username, String email) {
        super();
        this.username = username;
        this.email = email;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", username=" + username + ", email=" + email + "]";
    }
    
}
