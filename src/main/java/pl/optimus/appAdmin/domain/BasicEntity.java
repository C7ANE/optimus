package pl.optimus.appAdmin.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(onlyExplicitlyIncluded = false)

@MappedSuperclass
public class BasicEntity<I extends Serializable>
implements Serializable{

    @ToString.Include

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}