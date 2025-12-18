package com.example.spring_7_11.Controllers;

import com.example.spring_7_11.Service.LoggedUserManagementService;
import com.example.spring_7_11.Service.LoginCountService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    private final LoggedUserManagementService loggedUserManagementService;
    private final LoginCountService loginCountService;

    public MainController(
            LoggedUserManagementService loggedUserManagementService,
            LoginCountService loginCountService
    ) {
        this.loggedUserManagementService = loggedUserManagementService;
        this.loginCountService = loginCountService;
    }

    // /home
    @GetMapping("/home")
    public String home(Model page) {
        page.addAttribute("username", "Katy");
        page.addAttribute("color", "red");
        return "home.html";
    }

    // /home-req?color=blue
    @GetMapping("/home-req")
    public String homeWithRequestParam(
            @RequestParam String color,
            Model page
    ) {
        page.addAttribute("username", "Katy");
        page.addAttribute("color", color);
        return "home.html";
    }

    // /home-req2?name=Jane&color=green
    @GetMapping("/home-req2")
    public String homeWithOptionalParams(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color,
            Model page
    ) {
        String username = (name != null && !name.isBlank()) ? name : "Anonymous";
        String textColor = (color != null && !color.isBlank()) ? color : "black";

        page.addAttribute("username", username);
        page.addAttribute("color", textColor);
        return "home.html";
    }

    // /home/blue
    @GetMapping("/home/{color}")
    public String homeWithPathVariable(
            @PathVariable String color,
            Model page
    ) {
        page.addAttribute("username", "Katy");
        page.addAttribute("color", color);
        return "home.html";
    }

    // /main
    @GetMapping("/main")
    public String main(
            @RequestParam(required = false) String logout,
            Model model
    ) {
        if (logout != null) {
            loggedUserManagementService.setUsername(null);
            return "redirect:/";
        }

        String username = loggedUserManagementService.getUsername();

        if (username == null) {
            return "redirect:/";
        }

        int count = loginCountService.getCount();

        model.addAttribute("username", username);
        model.addAttribute("loginCount", count);

        return "main.html";
    }
}
