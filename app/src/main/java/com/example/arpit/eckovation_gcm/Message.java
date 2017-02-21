package com.example.arpit.eckovation_gcm;

/**
 * Created by arpit on 24-10-2015.
 */
public class Message {
    private String name;
    private String message;
    private String timestamp;

    public Message() {
    }

    public Message(String message, String timestamp,String name) {
        this.message = message;
        this.timestamp = timestamp;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name =name;
    }

}