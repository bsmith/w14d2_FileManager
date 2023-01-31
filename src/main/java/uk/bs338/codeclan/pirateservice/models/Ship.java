package uk.bs338.codeclan.pirateservice.models;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ships")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Ship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Type(type="org.hibernate.type.TextType")
    private String name;

    @OneToMany(mappedBy = "ship", fetch = FetchType.LAZY)
    //You can use JsonBackReference here as an alternative
//    @JsonIgnoreProperties({"ship"})
    @JsonBackReference
    private List<Pirate> pirates;

    public Ship(String name) {
        this.name = name;
    }

    public Ship() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pirate> getPirates() {
        return pirates;
    }

    public void setPirates(List<Pirate> pirates) {
        this.pirates = pirates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
