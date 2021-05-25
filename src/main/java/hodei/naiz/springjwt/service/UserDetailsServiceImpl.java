package hodei.naiz.springjwt.service;






import hodei.naiz.springjwt.model.CustomUser;
import hodei.naiz.springjwt.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

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
    public UserDetails loadUserByUsername(String username){
        CustomUser customUser = userRepository.findByUsername(username);
                //userRepository.findByUsername(username);
        if(customUser ==null){
            throw new UsernameNotFoundException("Didn't find User!");
        }
        return new User(customUser.getUsername(), customUser.getPassword(), emptyList());
    }
}
