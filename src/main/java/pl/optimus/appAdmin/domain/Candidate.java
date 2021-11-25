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
import java.io.Serial;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true,callSuper = true)
@ToString(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "candidates")
public class Candidate extends AbstractEntity {
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




}