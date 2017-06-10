package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Lenovo on 10.06.2017.
 */
@Controller
public class SecureController {

        @GetMapping("/") // to samo co @RequestMapping(value = "/", method = GET)
        public String main(){
            return "index";
        }

        @GetMapping("/login")
        public String loginPage() {
            return "login";
        }

        @GetMapping("/admin")
        public String adminPage() {
            return "adminview";
        }

        @GetMapping("/403")
        @ResponseBody
        public String error403() {
            return "<center><h1>Nie masz dostępu do tej strony</h1></center>";
        }
}
