package com.bipedalprogrammer.quicknotes;

import com.bipedalprogrammer.quicknotes.model.Card;
import com.bipedalprogrammer.quicknotes.model.Deck;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;

public class DeckServiceTest {
    @Mock
    private DeckRepository deckRepository;

    @Before
    public void before() {
        Deck myDeck  = new Deck(1, "Test", new Timestamp(1L), new Timestamp(2L));
        List<Deck> myDecks = new ArrayList<>();
        myDecks.add(myDeck);
        List<Card> cards = Arrays.asList(new Card(), new Card());
        when(deckRepository.getDecks()).thenReturn(myDecks);
        when(deckRepository.getCards(anyLong())).thenReturn(cards);
    }

    @Test
    public void deckMetadataExists() {
        DeckService service = new DeckService(deckRepository);
        List<Deck> decks = service.getDecks();
        assertThat(decks.size(), equalTo(1));
        assertThat(decks.get(0).getAccessed(), isNotNull());
    }

    @Test
    public void cardRetrievalUpdatesAccess() {
        DeckService service = new DeckService(deckRepository);
        Deck deck = service.getDeck(1L);
        List<Card> cards = service.getCards(1L);
        assertThat(deck.getAccessed(), greaterThan(new Timestamp(2)));
    }

}
