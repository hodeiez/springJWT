package hodei.naiz.springjwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Hodei Eceiza
 * Date: 5/28/2021
 * Time: 11:56
 * Project: springJWT
 * Copyright: MIT
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="roles")
public class CustomRoles {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String roleType;

   // @ManyToMany(mappedBy="roles") //it gave errors when starting the database, and still not necessary
    //private Set<CustomUser> users;
}
