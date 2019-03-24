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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@Column(nullable = false)
    private String country;

@Column(nullable = false)
    private String city;

@Column(nullable = false)
    private String street;

@Column(nullable = false)
    private String numberOfHouse;


    @OneToMany(mappedBy = "address")
    private List<Pet> pets = new ArrayList<>();

    @OneToOne(mappedBy = "address")
    private Shelter shelter ;


}
