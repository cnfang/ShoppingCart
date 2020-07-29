package idv.cnfang.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="price", nullable=false)
    private Double price;
    
    @Column(name="name", length=255, nullable=false)
    private String name;
    
    @Column(name="description", length=1000, nullable=false)
    private String description;
    
    @Column(name="image", length=Integer.MAX_VALUE, nullable=false)
    private String image;
    
    
    public Product() {
    }

    
    /**
     * 
     * @param price, price
     * @param name, name
     * @param description, description
     * @param image, image
     */
    public Product(Double price, String name, String description, String image) {
        super();
        this.price = price;
        this.name = name;
        this.description = description;
        this.image = image;
    }


    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public Double getPrice() {
        return price;
    }



    public void setPrice(Double price) {
        this.price = price;
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getDescription() {
        return description;
    }



    public void setDescription(String description) {
        this.description = description;
    }



    public String getImage() {
        return image;
    }



    public void setImage(String image) {
        this.image = image;
    }



    @Override
    public String toString() {
        return "Product [id=" + id + ", price=" + price + ", name=" + name + ", Description=" + description + ", image="
                + image + "]";
    }
    
    
    
}
