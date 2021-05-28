package hodei.naiz.springjwt.service;






import hodei.naiz.springjwt.model.CustomRoles;
import hodei.naiz.springjwt.model.CustomUser;
import hodei.naiz.springjwt.repo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by Hodei Eceiza
 * Date: 5/23/2021
 * Time: 21:35
 * Project: springJWT
 * Copyright: MIT
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        CustomUser customUser = userRepository.findByUsername(username);
        //userRepository.findByUsername(username);
        if (customUser == null) {
            throw new UsernameNotFoundException("Didn't find User!");
        }
        return new User(customUser.getUsername(), customUser.getPassword(), getAuthorities(customUser)); //added authorities
    }

    private Collection<GrantedAuthority> getAuthorities(CustomUser user) {
        Set<CustomRoles> userRoles = user.getRoles();
        Collection<GrantedAuthority> authorities = new ArrayList<>(userRoles.size());
        for (CustomRoles usersRoles : userRoles) {
            authorities.add(new SimpleGrantedAuthority(usersRoles.getRoleType().toUpperCase()));
            System.out.println(usersRoles.getRoleType().toUpperCase());
        }

        return authorities;
    }
}
