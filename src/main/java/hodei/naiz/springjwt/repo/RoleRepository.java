package hodei.naiz.springjwt.repo;

import hodei.naiz.springjwt.model.CustomRoles;
import hodei.naiz.springjwt.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Hodei Eceiza
 * Date: 5/28/2021
 * Time: 21:23
 * Project: springJWT
 * Copyright: MIT
 */
public interface RoleRepository extends JpaRepository<CustomRoles,String> {
    CustomRoles findByRoleType(String role);
}
