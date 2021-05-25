package hodei.naiz.springjwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

/**
 * Created by Hodei Eceiza
 * Date: 5/22/2021
 * Time: 22:47
 * Project: springJWT
 * Copyright: MIT
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class CustomUser {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;

  @Column(name="email", nullable = false,unique = true)
    private String email;
}
