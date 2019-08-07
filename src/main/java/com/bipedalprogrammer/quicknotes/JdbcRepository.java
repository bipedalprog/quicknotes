package com.bipedalprogrammer.quicknotes;

import com.bipedalprogrammer.quicknotes.model.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Deck> getDecks() {
        return jdbcTemplate.query("select id, name from deck", (resultSet, i) -> {
           return toDeck(resultSet);
        });
    }

    private Deck toDeck(ResultSet resultSet) throws SQLException {
        Deck deck = new Deck();
        deck.setId(resultSet.getLong("ID"));
        deck.setName(resultSet.getString("NAME"));
        return deck;
    }
}
