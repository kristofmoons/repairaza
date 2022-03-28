package be.thomasmore.repairaza.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;


@Entity
public class Taxateur {
    @Id
    private Integer id;
    private String name;
    private String bio;

    @OneToMany(mappedBy = "taxateurs")
    private Collection<Item> item;

    public Taxateur() {
    }

    public Taxateur(int id) {
        this.id=id;
    }

    public Taxateur(Integer id, String name, String bio) {
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

    public Collection<Item> getItem() {
        return item;
    }

    public void setItem(Collection<Item> item) {
        this.item = item;
    }
}
