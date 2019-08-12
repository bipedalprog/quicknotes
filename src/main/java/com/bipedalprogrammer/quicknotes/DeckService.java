package com.bipedalprogrammer.quicknotes;

import com.bipedalprogrammer.quicknotes.model.Card;
import com.bipedalprogrammer.quicknotes.model.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {
    private JdbcRepository jdbcRepository;

    @Autowired
    public DeckService(JdbcRepository jdbcRepository) {
        this.jdbcRepository = jdbcRepository;
    }

    public List<Deck> getCurrentDecks() {
        // TODO: just get most recent
        return jdbcRepository.getDecks();
    }

    public Deck getDeck(Long id) {
        return jdbcRepository.getDeck(id);
    }

    public List<Card> getCards(Long id) { return jdbcRepository.getCards(id); }
}
