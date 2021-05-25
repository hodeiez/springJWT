package hodei.naiz.springjwt.service;

import hodei.naiz.springjwt.model.CustomUser;
import hodei.naiz.springjwt.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Created by Hodei Eceiza
 * Date: 5/22/2021
 * Time: 23:01
 * Project: springJWT
 * Copyright: MIT
 */
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public CustomUser saveUser(CustomUser customUser){
        return userRepository.save(customUser);
    }
}
