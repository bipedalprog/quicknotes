package com.bipedalprogrammer.quicknotes;

import com.bipedalprogrammer.quicknotes.model.Card;
import com.bipedalprogrammer.quicknotes.model.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {
    private DeckRepository jdbcRepository;

    @Autowired
    public DeckService(DeckRepository jdbcRepository) {
        this.jdbcRepository = jdbcRepository;
    }

    public List<Deck> getCurrentDecks() {
        return jdbcRepository.getRecentDecks(5);
    }

    public List<Deck> getDecks() {
        return jdbcRepository.getDecks();
    }

    public Deck getDeck(Long id) {
        return jdbcRepository.getDeck(id);
    }

    public List<Card> getCards(Long id) {
        jdbcRepository.updateAccessed(id);
        return jdbcRepository.getCards(id);
    }

    public boolean addCard(Card card) {
        if (jdbcRepository.insertCard(card)) {
            jdbcRepository.updateModified(card.getDeckId());
            return true;
        }
        return false;
    }
}
