package pl.optimus.appAdmin.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serial;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
@ToString(onlyExplicitlyIncluded = true)

@Entity
public class RegisterDetails extends AbstractEntity {

    @Serial
    private static final long serialVersionUID = 8539936152170848000L;


    @ToString.Include


    private String serialNumber;

    @ManyToOne
    @JoinColumn(name = "register_id")
    private Register register;


}
