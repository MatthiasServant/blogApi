package Model;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(fetch = FetchType.LAZY)
    private Collection<Post> posts;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Reaction> reactions;
}
