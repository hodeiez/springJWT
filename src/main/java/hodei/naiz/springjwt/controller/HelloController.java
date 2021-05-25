package hodei.naiz.springjwt.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hodei Eceiza
 * Date: 5/25/2021
 * Time: 22:38
 * Project: springJWT
 * Copyright: MIT
 */

/**
 * secured api (check WebSecurity)
 */
@RestController
@RequestMapping("/hello")
@AllArgsConstructor
public class HelloController {
    @GetMapping()
    public String hello(){
        return "hello";
    }
}
