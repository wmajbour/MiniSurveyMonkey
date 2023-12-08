package org.MiniSurveyMonkey.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class WebController {

    @GetMapping("/")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }

    @GetMapping("/surveyor")
    public String showSurveyor() {
        return "Surveyor";
    }
    
    @GetMapping("/error")
    public String showErrorPage() {
        
        return "error";
    }

}
