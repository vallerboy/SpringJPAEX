package com.example.demo.controllers;

import com.example.demo.models.UserInfoBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Lenovo on 10.06.2017.
 */
@Controller
public class SecureController {

    @Autowired
    UserInfoBean userInfo;


        @GetMapping("/user/{message}")
        @ResponseBody
        public String setUserInfo(@PathVariable("message") String message){
            userInfo.setMessage(message);
            return "Ustawiłem tekst dla Ciebie: " +  message;
        }

         @GetMapping("/user")
         @ResponseBody
         public String getUserInfo(){
             return "Twój tekst to: " + userInfo.getMessage();
         }

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
