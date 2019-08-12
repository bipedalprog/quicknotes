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
public class JdbcRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Deck> getDecks() {
        return jdbcTemplate.query("select id, name from decks", (resultSet, i) -> {
           return toDeck(resultSet);
        });
    }

    public Deck getDeck(Long id) {
        return jdbcTemplate.query("select id, name from decks where id = ?", (resultSet, i) -> {
            return toDeck(resultSet);
        }, id).get(0);
    }

    public List<Card> getCards(long deckId) {
        return jdbcTemplate.query(("select id, deck_id, title, content from cards where deck_id = ?"),
                (resultSet, i) -> { return toCard(resultSet); }, deckId);
    }

    private Deck toDeck(ResultSet resultSet) throws SQLException {
        Deck deck = new Deck();
        deck.setId(resultSet.getLong("ID"));
        deck.setName(resultSet.getString("NAME"));
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
