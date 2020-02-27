package denis.lishchuk.pets.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @NotNull
    private Role role;

@OneToOne(mappedBy = "admin")
    private Shelter shelter;


}
