package be.thomasmore.repairaza.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Item {
    @Id
    private Integer id;

    private String itemName;
    private String soortObject;
    private String itemDetails;
    private boolean InStock;
    private double price;

    @ManyToMany (fetch = FetchType.LAZY)
    private Collection<Restaureur>restaureurs;

    @ManyToOne (fetch = FetchType.LAZY)
    private Taxateur taxateurs;


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

    public String getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(String itemDetails) {
        this.itemDetails = itemDetails;
    }

    public Collection<Restaureur> getRestaureurs() {
        return restaureurs;
    }

    public void setRestaureurs(Collection<Restaureur> restaureurs) {
        this.restaureurs = restaureurs;
    }

    public Taxateur getTaxateurs() {
        return taxateurs;
    }

    public void setTaxateurs(Taxateur taxateurs) {
        this.taxateurs = taxateurs;
    }
}
