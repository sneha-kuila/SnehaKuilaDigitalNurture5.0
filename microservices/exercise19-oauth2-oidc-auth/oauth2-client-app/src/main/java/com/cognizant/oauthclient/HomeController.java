package com.cognizant.oauthclient;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

}

/**
 * Separate @RestController exposing the raw authenticated principal as JSON,
 * useful for quickly inspecting what OAuth2/OIDC gives you after login.
 */
@RestController
class UserController {

    @GetMapping("/user")
    public Object user(Principal principal) {
        if (principal instanceof OidcUser oidcUser) {
            return oidcUser.getClaims();
        } else if (principal instanceof OAuth2User oAuth2User) {
            return oAuth2User.getAttributes();
        }
        return principal;
    }

}
