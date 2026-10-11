package com.example.graphql;

public class Droid extends BaseCharacter {
    private String primaryFunction;

    public Droid(Long id, String name, String primaryFunction) {
        super(id, name);
        this.primaryFunction = primaryFunction;
    }

    public String getPrimaryFunction() {
        return primaryFunction;
    }

    public void setPrimaryFunction(String primaryFunction) {
        this.primaryFunction = primaryFunction;
    }
}
