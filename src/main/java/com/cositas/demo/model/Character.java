package com.cositas.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Entity
@Table(name = "characters")
public class Character {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    private long id;

    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "class", nullable = false)
    private String className;

    //@Column(name = "stats", nullable = false)
    //private List<Integer> stats;

    @Column(name = "alignment", nullable = false)
    private String alignment;

    public Character(long id, UUID uuid, String name, String className, /*List<Integer> stats,*/ String alignment) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.className = className;
        //this.stats = stats;
        this.alignment = alignment;
    }
}
