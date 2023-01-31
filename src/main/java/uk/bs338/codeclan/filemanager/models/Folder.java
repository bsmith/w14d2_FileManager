package uk.bs338.codeclan.filemanager.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "folders")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Folder {
    /* By using an object here, it can be null */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* the type suggestion is from https://dev.to/yugabyte/jpa-and-postgresql-text-2ma6 */
    @Column
    @Type(type="org.hibernate.type.TextType")
    private String title;

    @OneToMany(mappedBy = "folder", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<File> files;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    //You can use JsonBackReference here as an alternative
    @JsonManagedReference(value="person")
    private Person person;

    public Folder(String title, Person person) {
        this.title = title;
        this.files = new ArrayList<>();
        this.person = person;
        System.out.printf("* Folder(%s) = %s%n", this.formatFilename(), this);
    }

    public Folder() {
        System.out.printf("* Folder() = %s%n", this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String formatFilename() {
        return File.formatFilename(this.person.getName(), title, "", "");
    }
}
