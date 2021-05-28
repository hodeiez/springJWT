package hodei.naiz.springjwt.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hodei Eceiza
 * Date: 5/25/2021
 * Time: 23:00
 * Project: springJWT
 * Copyright: MIT
 */
@RestController
@RequestMapping("/public")
@AllArgsConstructor
public class PublicController {
    @GetMapping()
    public String hello(){
        return "hello this is a public API :)";
    }
    @GetMapping("/admin")
    public String forAdmin(){
        return "this is only for admin :)";

    }
    @GetMapping("/user")
    public String forUser(){
        return "this is only for user :)";
    }
}
