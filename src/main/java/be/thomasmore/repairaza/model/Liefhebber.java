package be.thomasmore.repairaza.model;


import javax.persistence.*;
import java.util.Collection;

@Entity
public class Liefhebber {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "liefhebber_generator")
    @SequenceGenerator(name = "liefhebber_generator", sequenceName = "liefhebber_seq", allocationSize = 1)
    @Id
    private Integer id;
    private String nickName;

    @ManyToMany(mappedBy = "liefhebbers")
    private Collection<Item> items;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;


    public Liefhebber() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public void setItems(Collection<Item> items) {
        this.items = items;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
