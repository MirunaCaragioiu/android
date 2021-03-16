package com.example.facturi;


public class FeedbackClass {
    String email;
    String feedback;
    float rating;

    @Override
    public String toString() {
        return "email=" + email  +
                ", feedback=" + feedback +
                ", rating=" + rating + '.';
    }

    public FeedbackClass(String email, String feedback, float rating) {
        this.email = email;
        this.feedback = feedback;
        this.rating = rating;
    }

    public FeedbackClass() {
        this.email = "";
        this.feedback = "";
        this.rating = 0;
    }

    public String getEmail() {
        return email;
    }

    public String getFeedback() {
        return feedback;
    }

    public float getRating() {
        return rating;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
