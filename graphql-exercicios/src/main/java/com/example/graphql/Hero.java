package com.example.graphql;

import java.util.List;

/**
 * Hero
 */
public class Hero {
    private Long id;
    private String name;
    private List<String> appearsIn;

    public Hero(Long id, String name) {
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

}
