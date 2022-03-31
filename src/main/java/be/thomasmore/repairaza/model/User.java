package be.thomasmore.repairaza.model;

import javax.persistence.*;

@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_seq", allocationSize = 1)
    @Id
    private Integer id;

    String username;
    String password;
    String role;

    @OneToOne(mappedBy = "user", optional = false)
    private Liefhebber liefhebber;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Liefhebber getLiefhebber() {
        return liefhebber;
    }

    public void setLiefhebber(Liefhebber liefhebber) {
        this.liefhebber = liefhebber;
    }
}