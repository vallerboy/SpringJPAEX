package com.example.demo.controllers;

import com.example.demo.UserRepository;
import com.example.demo.models.User;
import com.example.demo.models.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by Lenovo on 10.06.2017.
 */
@Controller
public class SecureController {

         @Autowired
         UserInfo userInfo;

         @Autowired
         UserRepository userRepository;


         @GetMapping("/checkuser")
         @ResponseBody
         public String checkUser() {
             return "Czy user jest zalogowany?: " + userInfo.isLogged();
         }


        @GetMapping("/") // to samo co @RequestMapping(value = "/", method = GET)
        public String main(){
            if(userInfo.isLogged()) {
                System.out.println("Rola: " + userInfo.getUser().getRole());
            }


             return "index";
        }

        @GetMapping("/login")
        public String loginPage(Model model) {
            return "login";
        }

        @PostMapping("/login")
        public String postLoginPage(@RequestParam("password") String password,
                                    @RequestParam("login") String login,
                                    Model model) {
            if(userInfo.isLogged()){
                return "index";
            }

            Optional<User> user = userRepository.findByUsername(login);

            if(password.equals(user.get().getPassword())) {
                userInfo.setLogged(true);
                userInfo.setUser(user.get());
                model.addAttribute("info", "Poprawnie zalogowano");
            }
            model.addAttribute("info", "Błędny login lub hasło");
            return "login";
        }

        @GetMapping("/logout")
        @ResponseBody
        public String logout() {
            userInfo.setLogged(false);
            return "<center><h1>Wylogowano!</center></h1>";
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
