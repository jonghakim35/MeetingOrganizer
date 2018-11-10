package com.example.jongha.meetingorganizer;

public class ScheduleDTO {

    private String startHour, endHour, startMin, endMin, dayOfWeek, activityName;
    private String ListViewTime;

    public ScheduleDTO(){

    }

    public ScheduleDTO(String activityName, String startHour, String startMin, String endHour, String endMin, String dayOfWeek){
        this.activityName = activityName;
        this.startHour = startHour;
        this.startMin = startMin;
        this.endHour = endHour;
        this.endMin = endMin;
        this.dayOfWeek = dayOfWeek;
    }

    public String getStartHour() { return startHour; }

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

    public String getListViewTime() { return dayOfWeek + "요일  " + startHour+":"+startMin+"~"+endHour+":"+endMin; }

    public String getActivityName() {return activityName;}

    //listview, schedule upload/delete사용
}
