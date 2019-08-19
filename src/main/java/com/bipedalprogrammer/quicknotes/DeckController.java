package com.bipedalprogrammer.quicknotes;

import com.bipedalprogrammer.quicknotes.model.Card;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/deck")
public class DeckController {
    private final DeckService deckService;
    private final Logger logger = LoggerFactory.getLogger(DeckController.class);

    @Autowired
    private DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    @GetMapping("{id}")
    public String getDeck(@PathVariable String id, Model model) {
        Long deckId = Long.valueOf(id);
        model.addAttribute("deck", deckService.getDeck(deckId));
        model.addAttribute("cards", deckService.getCards(deckId));
        return "deck";
    }

    @PostMapping(value = "{id}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String addCard(@PathVariable String id, @RequestParam Map<String, String> cardMap) {
        Long deckId = Long.valueOf(id);
        logger.info("Adding a new card to deck " + deckId);
        Card card = new Card();
        card.setContent(cardMap.get("content"));
        card.setTitle(cardMap.get("title"));
        card.setDeckId(deckId);
        deckService.addCard(card);
        return "redirect:"+deckId;
    }
}
