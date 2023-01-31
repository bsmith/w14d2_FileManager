package uk.bs338.codeclan.filemanager.models;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Person {
    /* By using an object here, it can be null */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @Type(type="org.hibernate.type.TextType")
    private String name;

    @OneToMany(mappedBy = "person", fetch = FetchType.LAZY)
//    @JsonManagedReference("person_folders")
    @JsonIgnore
    private List<Folder> folders;

    public Person(String name) {
        this.name = name;
        this.folders = new ArrayList<>();
        System.out.printf("* Person(%s) = %s%n", name, this);
    }

    public Person() {
        System.out.printf("* Person() = %s%n", this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void setFolders(List<Folder> folders) {
        this.folders = folders;
    }
}
