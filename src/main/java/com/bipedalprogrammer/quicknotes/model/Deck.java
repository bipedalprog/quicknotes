package com.bipedalprogrammer.quicknotes.model;

import java.sql.Timestamp;

public class Deck {
    private long id;
    private String name;
    private Timestamp updated;
    private Timestamp accessed;

    public Deck() {}

    public Deck(long id, String name, Timestamp updated, Timestamp accessed) {
        this.id = id;
        this.name = name;
        this.updated = updated;
        this.accessed = accessed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getUpdated() {
        return updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Timestamp getAccessed() {
        return accessed;
    }

    public void setAccessed(Timestamp accessed) {
        this.accessed = accessed;
    }
}
