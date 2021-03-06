package hodei.naiz.springjwt.controller;

import hodei.naiz.springjwt.model.CustomUser;
import hodei.naiz.springjwt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Hodei Eceiza
 * Date: 5/22/2021
 * Time: 23:00
 * Project: springJWT
 * Copyright: MIT
 */
@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> saveUser(@RequestBody CustomUser customUser){
        //TODO: use a dto to save the password encoded

        String pass=bCryptPasswordEncoder.encode(customUser.getPassword());
        customUser.setPassword(pass);
        userService.saveUser(customUser);
       return ResponseEntity.ok(customUser.getFirstName()+" has been saved");
    }

}
