package com.litton.ishirdaily.eventbus;

public class ActivityFinishEvent {
    private String message;
    public ActivityFinishEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

