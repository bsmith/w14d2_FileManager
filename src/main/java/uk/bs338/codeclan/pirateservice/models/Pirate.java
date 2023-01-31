package uk.bs338.codeclan.pirateservice.models;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pirates")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pirate{

    /* the type suggestion is from https://dev.to/yugabyte/jpa-and-postgresql-text-2ma6 */
    @Column(name = "first_name")
    @Type(type="org.hibernate.type.TextType")
    private String firstName;

    @Column(name = "last_name")
    @Type(type="org.hibernate.type.TextType")
    private String lastName;

    @Column(name = "age")
    private int age;

    /* By using an object here, it can be null */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ship_id", nullable = false)
    //You can use JsonBackReference here as an alternative
//    @JsonIgnoreProperties({"pirates"})
//    @JsonManagedReference(value="ship")
    private Ship ship;

    @ManyToMany
    //You can use JsonBackReference here as an alternative
//    @JsonIgnoreProperties({"pirates"})
    @JsonBackReference(value="raids")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    @JoinTable(
            name = "pirates_raids",
            joinColumns = {@JoinColumn(name = "pirate_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "raid_id", nullable = false, updatable = false)}
    )
    private List<Raid> raids;

    public Pirate(String firstName, String lastName, int age, Ship ship) {
        System.out.printf("* Pirate(%s,%s,%s,%s) = %s%n", firstName, lastName, age, ship, this);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.ship = ship;
        this.raids = new ArrayList<Raid>();
    }

    public Pirate(){
        System.out.printf("* Pirate() = %s%n", this);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public List<Raid> getRaids() {
        return raids;
    }

    public void setRaids(List<Raid> raids) {
        this.raids = raids;
    }

    public void addRaid(Raid raid){
        this.raids.add(raid);
    }

}
