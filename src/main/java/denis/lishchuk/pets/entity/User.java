package denis.lishchuk.pets.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@Column(name = "_login", unique = true, nullable = false)
    private String login;

@Column(name = "_password", nullable = false)
    private String password;

@OneToOne(mappedBy = "admin")
    private Shelter shelter;


}
