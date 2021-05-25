package hodei.naiz.springjwt.config;

import org.springframework.stereotype.Component;

/**
 * Created by Hodei Eceiza
 * Date: 5/23/2021
 * Time: 21:15
 * Project: springJWT
 * Copyright: MIT
 */

public class SecurityConstants {

   //used https://www.allkeysgenerator.com/ to create the secret key ("512-bit" long, same as the encoding algorithm)
    public static final String SECRET = "Yp3s6v9y$B&E)H@McQfThWmZq4t7w!z%C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeS";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/user/register";
    public static final String PUBLIC_URL = "/public";
}
