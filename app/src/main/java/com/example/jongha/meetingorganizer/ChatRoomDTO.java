package com.example.jongha.meetingorganizer;

public class ChatRoomDTO {

    private String roomCode, estHour, estMin;

    public ChatRoomDTO(){

    }

    public ChatRoomDTO(String roomCode, String estHour, String estMin) {
        this.roomCode = roomCode;
        this.estHour = estHour;
        this.estMin = estMin;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public String getEstHour() {
        return estHour;
    }

    public String getEstMin() {
        return estMin;
    }
}
