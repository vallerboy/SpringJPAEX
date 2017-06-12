package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Lenovo on 10.06.2017.
 */
@Controller
public class SecureController {

         @Autowired
         UserInfo userInfo;


         @GetMapping("/checkuser")
         @ResponseBody
         public String checkUser() {
             Authentication auth = SecurityContextHolder.getContext().getAuthentication();

             return "Czy user jest zalogowany?: " + auth.getPrincipal();
         }


        @GetMapping("/") // to samo co @RequestMapping(value = "/", method = GET)
        public String main(){
            return "index";
        }

        @GetMapping("/login")
        public String loginPage(Model model) {
            return "login";
        }

        @PostMapping("/login")
        public String postLoginPage(@RequestParam("password") String password,
                                    @RequestParam("login") String login) {
            System.out.println("Login: " + login + " Hasło: " + password);
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
