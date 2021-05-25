package hodei.naiz.springjwt.repo;

import hodei.naiz.springjwt.model.CustomUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Hodei Eceiza
 * Date: 5/22/2021
 * Time: 22:56
 * Project: springJWT
 * Copyright: MIT
 */
public interface UserRepository extends JpaRepository<CustomUser,String> {
    CustomUser findByUsername(String username);
}
