package com.example.jongha.meetingorganizer;

public class ChatRoomDTO {

    private String roomCode, estHour, estMin, roomName;

    public ChatRoomDTO(){

    }

    public ChatRoomDTO(String roomName, String roomCode, String estHour, String estMin) {
        this.roomName = roomName;
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

    public String getRoomName() { return roomName; }
}
