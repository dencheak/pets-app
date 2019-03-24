package denis.lishchuk.pets.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private User admin;

    @OneToOne
    private Address address;

    @OneToMany(mappedBy = "shelter")
    private List<Pet> pets = new ArrayList<>();


}
