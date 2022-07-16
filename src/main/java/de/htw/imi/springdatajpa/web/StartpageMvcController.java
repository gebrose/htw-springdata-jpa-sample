package de.htw.imi.springdatajpa.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartpageMvcController {

    @GetMapping("/ui")
    String findAll() {
        return "startpage";
    }

    @GetMapping
    String root() {
        return "redirect:ui";
    }

}
