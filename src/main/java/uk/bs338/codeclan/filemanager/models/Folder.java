package uk.bs338.codeclan.filemanager.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "raids")
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="location")
    @Type(type="org.hibernate.type.TextType")
    private String location;

    @Column(name = "loot")
    private int loot;

    @ManyToMany
    //You can use JsonBackReference here as an alternative
//    @JsonIgnoreProperties({"raids"})
    @JsonBackReference
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    @JoinTable(
            name = "pirates_raids",
            joinColumns = {@JoinColumn(name = "raid_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name="pirate_id", nullable = false, updatable = false)}
    )
    private List<File> files;

    public Folder(String location, int loot) {
        this.location = location;
        this.loot = loot;
        this.files = new ArrayList<File>();
    }

    public Folder() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLoot() {
        return loot;
    }

    public void setLoot(int loot) {
        this.loot = loot;
    }

    public List<File> getPirates() {
        return files;
    }

    public void setPirates(List<File> files) {
        this.files = files;
    }

    public void addPirate(File file){
        this.files.add(file);
    }
}
