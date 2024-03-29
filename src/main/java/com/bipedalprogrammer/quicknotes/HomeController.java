package com.bipedalprogrammer.quicknotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    private DeckService deckService;

    @Autowired
    public HomeController(DeckService deckService) {
        this.deckService = deckService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("decks", deckService.getCurrentDecks());
        return "index";
    }
}
