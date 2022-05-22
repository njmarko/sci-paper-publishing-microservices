package rs.njmarko.userservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank(message = "Field 'id' cannot be empty!")
    private Integer id;

    @Column(name = "username", nullable = false)
    @NotBlank(message = "Field 'username' cannot be empty!")
    private String username;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Field 'password' cannot be empty!")
    private String password;

    @Column(name = "first_name", nullable = false)
    @NotBlank(message = "Field 'firstName' cannot be empty!")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @NotBlank(message = "Field 'lastName' cannot be empty!")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "is_logged_in")
    private Boolean isLoggedIn = Boolean.FALSE;
}
