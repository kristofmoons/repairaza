package be.thomasmore.repairaza.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class worker {
    @Id
    private Integer id;
    private String name;
    private String function;
    private String bio;

    public worker() {
    }

    public worker(Integer id, String name, String function, String bio) {
        this.id = id;
        this.name = name;
        this.function = function;
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

    public void setName(String naam) {
        this.name = naam;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String functie) {
        this.function = functie;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
