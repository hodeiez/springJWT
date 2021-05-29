package hodei.naiz.springjwt.service;

import hodei.naiz.springjwt.model.CustomRoles;
import hodei.naiz.springjwt.model.CustomUser;
import hodei.naiz.springjwt.repo.RoleRepository;
import hodei.naiz.springjwt.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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
    private final RoleRepository roleRepository;
    public CustomUser saveUser(CustomUser customUser){
        return userRepository.save(customUser);
    }
    public CustomUser register(CustomUser user){
            CustomUser userWithRoles=insertRoles(user);
            return userRepository.save(userWithRoles);
    }
    private CustomUser insertRoles(CustomUser customUser){
        CustomRoles roles=roleRepository.findByRoleType("user");
        Set<CustomRoles> rolesSet=new HashSet<>();
        rolesSet.add(roles);
        customUser.setRoles(rolesSet);
        return customUser;
    }
    public CustomUser findUser(String username){
        return userRepository.findByUsername(username);
    }
    public CustomUser getOwnDetails(){
        //TODO: use dto to retrieve user data without password
      return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }
}
