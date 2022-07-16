package de.htw.imi.springdatajpa.web;

import de.htw.imi.springdatajpa.repos.BueroRaumRepository;
import de.htw.imi.springdatajpa.services.BueroRaumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BueroRaumMvcController {

    @Autowired
    BueroRaumRepository bueroRaumRepository;

    @Autowired
    BueroRaumService bueroRaumService;

    @GetMapping(path = "/ui/bueros")
    String findAll(final Model model) {
        model.addAttribute("bueros", bueroRaumRepository.findAll());
        // empty template object that accepts fiel values from
        // the HTML form when new office room objetcs are created
        model.addAttribute("bueroTemplate", new BueroDto());
        return "buero-list";
    }

    @GetMapping("/ui/bueros/{id}")
    String find(final Model model,
                @PathVariable("id") final Long id) {
        model.addAttribute("buero",
                bueroRaumRepository
                        .findById(id)
                        .orElseThrow(IllegalArgumentException::new));
        return "buero-detail.html";
    }

    @PostMapping("/ui/bueros")
    String createBuero(@ModelAttribute("bueroTemplate") final BueroDto bueroTemplate) {
        bueroRaumService.createFrom(bueroTemplate);
        // causes a page reload
        return "redirect:/ui/bueros";
    }

    @DeleteMapping("/ui/bueros/{id}")
    String deleteBuero(@PathVariable("id") final Long id) {
        bueroRaumRepository
                .findById(id)
                .ifPresent(b -> bueroRaumRepository.delete(b));
        // causes a page reload
        return "redirect:/ui/bueros";
    }

}
