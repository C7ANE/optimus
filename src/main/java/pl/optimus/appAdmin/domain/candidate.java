package pl.optimus.appAdmin.domain;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
@ToString(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "candidates")
public class candidate extends AbstractEntity {

    @ToString.Include

    @Column(length = 255)
    @NotNull
    @Size(min = 1,max = 20)
    private String firstName;

    @Column(length = 255)
    @Size(min = 1,max = 20)
    @NotNull
    private String lastName;

    @Email
    @NotNull
    private String email;




}
