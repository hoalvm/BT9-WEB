package vn.iotstar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    String login() {
        return "auth/login";
    }

    // POST /login is handled entirely by Spring Security — do NOT add @PostMapping here.
}
