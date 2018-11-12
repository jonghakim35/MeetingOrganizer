package com.example.jongha.meetingorganizer;

public class ChatDTO {
    private String userName, message;

    public ChatDTO(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public ChatDTO() {
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }
}
