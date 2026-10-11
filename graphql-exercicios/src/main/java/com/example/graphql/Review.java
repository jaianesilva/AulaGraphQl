package com.example.graphql;

public class Review {
    private Long id;
    private Integer stars;
    private String commentary;

    public Review(Long id, Integer stars, String commentary) {
        this.id = id;
        this.stars = stars;
        this.commentary = commentary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }
}