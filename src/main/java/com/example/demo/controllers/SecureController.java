package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Lenovo on 10.06.2017.
 */
@Controller
public class SecureController {

        @GetMapping("/") // to samo co @RequestMapping(value = "/", method = GET)
        public String main(){
            return "index";
        }
}
