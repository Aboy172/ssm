package pro01.spring.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {



    @GetMapping("/main.html")
    public String main ( ) {
        return "main";
    }



}
