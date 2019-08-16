package com.bipedalprogrammer.quicknotes;

import com.bipedalprogrammer.quicknotes.model.Card;
import com.bipedalprogrammer.quicknotes.model.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

@Repository
public class JdbcRepository implements DeckRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Deck> getRecentDecks(int count) {
        return jdbcTemplate.query("select top ? id, name, updated, accessed from decks order by accessed",
                (resultSet, i) -> { return toDeck(resultSet); }, count);
    }

    @Override
    public List<Deck> getDecks() {
        return jdbcTemplate.query("select id, name, updated, accessed from decks", (resultSet, i) -> {
           return toDeck(resultSet);
        });
    }

    @Override
    public Deck getDeck(Long id) {
        return jdbcTemplate.query("select id, name, updated, accessed from decks where id = ?", (resultSet, i) -> {
            return toDeck(resultSet);
        }, id).get(0);
    }

    @Override
    public List<Card> getCards(long deckId) {
        updateAccessed(deckId);
        return jdbcTemplate.query(("select id, deck_id, title, content from cards where deck_id = ?"),
                (resultSet, i) -> { return toCard(resultSet); }, deckId);
    }

    @Override
    public void updateAccessed(long deckId) {
        jdbcTemplate.update("UPDATE decks SET accessed = CURRENT_TIMESTAMP() WHERE id = ?", deckId);
    }

    private Deck toDeck(ResultSet resultSet) throws SQLException {
        Deck deck = new Deck();
        deck.setId(resultSet.getLong("ID"));
        deck.setName(resultSet.getString("NAME"));
        deck.setUpdated(resultSet.getTimestamp("UPDATED"));
        deck.setAccessed(resultSet.getTimestamp("ACCESSED"));
        return deck;
    }

    private Card toCard(ResultSet resultSet) throws SQLException {
        Card card = new Card();
        card.setId(resultSet.getLong("ID"));
        card.setDeckId(resultSet.getLong("DECK_ID"));
        card.setTitle(resultSet.getString("TITLE"));
        InputStream is = resultSet.getAsciiStream("CONTENT");
        String content =
                new Scanner(is, "UTF-8").useDelimiter("\\A").next();
        card.setContent(content);
        return card;
    }
}
