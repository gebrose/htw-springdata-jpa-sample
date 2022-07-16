package de.htw.imi.springdatajpa.web;

import de.htw.imi.springdatajpa.repos.ProfessorRepository;
import de.htw.imi.springdatajpa.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfessorMvcController {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    ProfessorService professorService;

    @GetMapping(path = "/ui/professoren")
    String findAll(final Model model) {
        model.addAttribute("professoren", professorRepository.findAll());
        // empty template object that accepts fiel values from
        // the HTML form when new office room objetcs are created
        model.addAttribute("professorTemplate", new ProfessorDto());
        return "professor-list";
    }

    @GetMapping("/ui/professoren/{id}")
    String find(final Model model,
                @PathVariable("id") final Long id) {
        model.addAttribute("professor",
                professorRepository
                        .findById(id)
                        .orElseThrow(IllegalArgumentException::new));
        return "professor-detail.html";
    }

    @PostMapping("/ui/professoren")
    String createBuero(@ModelAttribute("professorTemplate") final ProfessorDto professorDto) {
        professorService.createFrom(professorDto);
        // causes a page reload
        return "redirect:/ui/professoren";
    }

    @DeleteMapping("/ui/professoren/{id}")
    String deleteBuero(@PathVariable("id") final Long id) {
        professorRepository
                .findById(id)
                .ifPresent(b -> professorRepository.delete(b));
        // causes a page reload
        return "redirect:/ui/professoren";
    }

}
