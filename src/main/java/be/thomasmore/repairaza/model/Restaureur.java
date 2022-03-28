package be.thomasmore.repairaza.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Entity
public class Restaureur {
    @Id
    private Integer id;
    private String name;
    private String bio;

    @ManyToMany(mappedBy = "restaureurs")
    private Collection<Item> items;

    public Restaureur() {
    }

    public Restaureur(Integer id, String name, String bio) {
        this.id = id;
        this.name = name;
        this.bio = bio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }
}
