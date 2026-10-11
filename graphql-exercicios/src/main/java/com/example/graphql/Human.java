package com.example.graphql;

public class Human extends BaseCharacter {
    private Double height;

    public Human(Long id, String name, Double height) {
        super(id, name);
        this.height = height;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
