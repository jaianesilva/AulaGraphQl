package com.example.graphql;

/**
 * ReviewInput
 */
public class ReviewInput {
    private Integer stars;
    private String commentary;

    public ReviewInput(Integer stars, String commentary) {
        this.stars = stars;
        this.commentary = commentary;
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
