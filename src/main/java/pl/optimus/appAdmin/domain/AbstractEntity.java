package pl.optimus.appAdmin.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = false)

@MappedSuperclass
public class AbstractEntity {

    @EqualsAndHashCode.Include
    @ToString.Include

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private  Long id;
}