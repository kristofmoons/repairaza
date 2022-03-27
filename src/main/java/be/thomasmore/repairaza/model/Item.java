package be.thomasmore.repairaza.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Item {
    @Id
    private Integer id;

    private String itemName;
    private String soortObject;
    private boolean InStock;
    private double price;

    @ManyToMany
    private Collection<worker>workers;


    public Item() {
    }

    public Item(String itemName, String soortObject, boolean inStock, double price) {
        this.itemName = itemName;
        this.soortObject = soortObject;
        InStock = inStock;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String name) {
        this.itemName = name;
    }

    public String getSoortObject() {
        return soortObject;
    }

    public void setSoortObject(String soortObject) {
        this.soortObject = soortObject;
    }

    public boolean isInStock() {
        return InStock;
    }

    public void setInStock(boolean inStock) {
        InStock = inStock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Collection<worker> getWorkers() {
        return workers;
    }

    public void setWorkers(Collection<worker> workers) {
        this.workers = workers;
    }
}
