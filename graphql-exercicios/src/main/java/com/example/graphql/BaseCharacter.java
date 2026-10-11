package com.example.graphql;

import java.util.ArrayList;
import java.util.List;

/**
 * Base comum de Human e Droid (interface Character no schema GraphQL).
 */
public abstract class BaseCharacter {
    private Long id;
    private String name;
    private List<String> appearsIn;
    private List<BaseCharacter> friends = new ArrayList<>();

    protected BaseCharacter(Long id, String name) {
        this.id = id;
        this.name = name;
        this.appearsIn = List.of("NEWHOPE", "EMPIRE", "JEDI");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAppearsIn() {
        return appearsIn;
    }

    public void setAppearsIn(List<String> appearsIn) {
        this.appearsIn = appearsIn;
    }

    public List<BaseCharacter> getFriends() {
        return friends;
    }

    public void setFriends(List<BaseCharacter> friends) {
        this.friends = friends;
    }
}
