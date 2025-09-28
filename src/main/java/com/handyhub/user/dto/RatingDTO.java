package com.handyhub.user.dto;

import java.time.LocalDateTime;

public class RatingDTO {

    private Long id;
    private Long ratedById;   // who gave rating
    private int score;        // 1–5
    private String comment;
    private LocalDateTime createdDate;

    private Long ratedToId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRatedById() {
        return ratedById;
    }

    public void setRatedById(Long ratedById) {
        this.ratedById = ratedById;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getRatedToId() {
        return ratedToId;
    }

    public void setRatedToId(Long ratedToId) {
        this.ratedToId = ratedToId;
    }
}
