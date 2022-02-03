package pl.optimus.appAdmin.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UplodedFile {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    private String fileId;

    private String fileName;

    private String fileType;
    @Lob
    private byte[] fileData;


}
