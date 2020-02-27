package denis.lishchuk.pets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin
@Controller
public class HtmlPageController {

    @RequestMapping("/homeAdmin")
    public String mainPageForAdmin(){
        return "indexAdmin.html";
    }

    @RequestMapping("/homeShelterAdmin")
    public String mainPageForShelterAdmin(){
        return "indexShelterAdmin.html";
    }

    @RequestMapping("/home")
    public String mainPage(){
        return "index.html";
    }

    @RequestMapping("/sign")
    public String singPage(){
        return "sign.html";
    }

    @RequestMapping("/registration")
    public String registrationPage(){
        return "registration.html";
    }
}
