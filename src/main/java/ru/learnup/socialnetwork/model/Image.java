package ru.learnup.socialnetwork.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="images")
public class Image {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "content_type")
    private String contentType;

    @Column(name = "size")
    private Long size;

    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    private byte[] bytes;


    @Override
    public String toString() {
        return "Image{" +
                "is=" + id +
                ", name='" + name + '\'' +
                ",content_type='" + contentType + '\'' +
                ", size='" + size + '\'' +
                ", bytes='" + bytes +
                '}';
    }

}
