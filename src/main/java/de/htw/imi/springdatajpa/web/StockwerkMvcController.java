package de.htw.imi.springdatajpa.web;

import de.htw.imi.springdatajpa.repos.StockwerkRepository;
import de.htw.imi.springdatajpa.services.StockwerkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StockwerkMvcController {

    @Autowired
    StockwerkRepository stockwerkRepository;

    @Autowired
    StockwerkService stockwerkService;

    @GetMapping(path = "/ui/stockwerke")
    String findAll(final Model model) {
        model.addAttribute("stockwerke", stockwerkRepository.findAll());
        // empty template object that accepts fiel values from
        // the HTML form when new office room objetcs are created
        model.addAttribute("stockwerkTemplate", new StockwerkDto());
        return "stockwerk-list";
    }

    @GetMapping("/ui/stockwerke/{id}")
    String find(final Model model,
                @PathVariable("id") final Long id) {
        model.addAttribute("stockwerke",
                stockwerkRepository
                        .findById(id)
                        .orElseThrow(IllegalArgumentException::new));
        return "stockwerk-detail.html";
    }

    @PostMapping("/ui/stockwerke")
    String createBuero(@ModelAttribute("stockwerkTemplate") final StockwerkDto stockwerkDto) {
        stockwerkService.createFrom(stockwerkDto);
        // causes a page reload
        return "redirect:/ui/stockwerke";
    }

    @DeleteMapping("/ui/stockwerke/{id}")
    String deleteBuero(@PathVariable("id") final Long id) {
        stockwerkRepository
                .findById(id)
                .ifPresent(b -> stockwerkRepository.delete(b));
        // causes a page reload
        return "redirect:/ui/stockwerke";
    }

}
