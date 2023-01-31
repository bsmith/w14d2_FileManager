package uk.bs338.codeclan.filemanager.models;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Entity
@Table(name = "files")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class File {
    /* By using an object here, it can be null */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* the type suggestion is from https://dev.to/yugabyte/jpa-and-postgresql-text-2ma6 */
    @Column(nullable = false)
    @Type(type="org.hibernate.type.TextType")
    private String name;

    @Column(length = 4)
    private String extension;

    @Column(nullable = false)
    private long size;

    @ManyToOne
    @JoinColumn(name = "folder_id", nullable = false)
    //You can use JsonBackReference here as an alternative
//    @JsonBackReference("file_folder")
    private Folder folder;

    public File(String name, String extension, long size, Folder folder) {
        this.name = name;
        this.extension = extension;
        this.size = size;
        this.folder = folder;
        System.out.printf("* File(%s,size=%s) = %s%n", this.formatFilename(), size, this);
    }

    public File(){
        System.out.printf("* File() = %s%n", this);
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Folder getFolder() {
        return folder;
    }

    public void setFolder(Folder folder) {
        this.folder = folder;
    }

    public static String formatFilename(String personName, String folderTitle, String fileName, String extension) {
        if (fileName == null) fileName = "";
        if (extension == null) extension = "";
        return String.format("$%s[%s]%s%s%s", personName, folderTitle, fileName,
                extension.length() > 0 ? ";" : "", extension);
    }

    /* Takes a function that might return null and lift it into a version that returns Optional instead */
    static public <U, T> Function<U, Optional<T>> liftNullable(Function<U, T> func) {
        return arg -> Optional.ofNullable(func.apply(arg));
    };

    @JsonGetter
    public String formatFilename() {
//        String folderTitle = this.folder != null ? this.folder.getTitle() : null;
//        String personName = this.folder != null && this.folder.getPerson() != null ?
//                this.folder.getPerson().getName() : null;
        String folderTitle = Optional.ofNullable(this.folder).map(Folder::getTitle).orElse(null);
        /* needs a lifted version of Optional.ofNullable? */
        String personName = Optional.ofNullable(this.folder)
//                .flatMap(folder -> Optional.ofNullable(folder.getPerson()))
                .flatMap(liftNullable(Folder::getPerson))
                .map(Person::getName).orElse(null);
        return formatFilename(personName, folderTitle, name, extension);
    }
}
