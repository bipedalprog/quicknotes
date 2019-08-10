package com.bipedalprogrammer.quicknotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deck")
public class DeckController {
    private final DeckService deckService;

    @Autowired
    private DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @GetMapping("{id}")
    public String getDeck(@PathVariable String id, Model model) {
        model.addAttribute("deck", deckService.getDeck(id));
        return "deck";
    }
}
