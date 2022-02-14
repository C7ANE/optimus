package pl.optimus.appAdmin.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
@ToString(onlyExplicitlyIncluded = true)

@Entity
public class User extends BasicEntity {
    @Serial
    private static final long serialVersionUID = 8539936152170847419L;
    @ToString.Include


    @NotNull
    @Size(min = 1,max = 20)
    private String firstName;


    @Size(min = 1,max = 20)
    @NotNull
    private String lastName;

    @Email
    @NotNull
    private String email;

    @Size(max=300)
    private String Content;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<UserSerialNumber> userDetailsList = new ArrayList<>();

    public void addDetails(String serialNumber){
        userDetailsList.add(new UserSerialNumber(serialNumber,this));
    }
}
