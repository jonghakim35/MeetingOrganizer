package com.example.jongha.meetingorganizer;

public class ScheduleDTO {

    private String startHour, endHour, startMin, endMin, dayOfWeek;

    public ScheduleDTO(){

    }

    public ScheduleDTO(String startHour, String startMin, String endHour, String endMin, String dayOfWeek){
        this.startHour = startHour;
        this.startMin = startMin;
        this.endHour = endHour;
        this.endMin = endMin;
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartHour() {
        return startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public String getStartMin() {
        return startMin;
    }

    public String getEndMin() {
        return endMin;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

}
