package com.bipedalprogrammer.quicknotes;

import com.bipedalprogrammer.quicknotes.model.Card;
import com.bipedalprogrammer.quicknotes.model.Deck;

import java.util.List;

public interface DeckRepository {
    List<Deck> getRecentDecks(int count);

    List<Deck> getDecks();

    Deck getDeck(Long id);

    List<Card> getCards(long deckId);
    boolean insertCard(Card card);

    void updateAccessed(long deckId);
    void updateModified(long deckId);
}
