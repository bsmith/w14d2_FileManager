package uk.bs338.codeclan.filemanager.models;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "files")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class File {
    /* By using an object here, it can be null */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* the type suggestion is from https://dev.to/yugabyte/jpa-and-postgresql-text-2ma6 */
    @Column
    @Type(type="org.hibernate.type.TextType")
    private String name;

    @Column(length = 4)
    private String extension;

    @Column
    private long size;

    @ManyToOne
    @JoinColumn(name = "folder_id", nullable = false)
    //You can use JsonBackReference here as an alternative
    @JsonManagedReference(value="folder")
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

    public String formatFilename() {
        return formatFilename(this.folder.getPerson().getName(), this.folder.getTitle(), name, extension);
    }
}
