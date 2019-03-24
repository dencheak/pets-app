package denis.lishchuk.pets.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
@Getter
@Setter
@NoArgsConstructor

@Entity
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Kind kind;

    @ManyToOne
    private Shelter shelter;
    @ManyToOne
    private Address address;

    @Column(nullable = false)
    private String imagePath;
}

