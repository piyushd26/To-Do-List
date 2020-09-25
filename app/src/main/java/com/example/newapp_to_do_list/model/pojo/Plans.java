package com.example.newapp_to_do_list.model.pojo;

public class Plans {

    String id;
    String name;
    String fromdate;
    String todate;
    String currenttime;
    String timepicked;
    String abouttask;
    int taskpending;

    public Plans(String id, String name, String fromdate, String todate, String currenttime, String timepicked, String abouttask) {
        this.id = id;
        this.name = name;
        this.fromdate = fromdate;
        this.todate = todate;
        this.currenttime = currenttime;
        this.timepicked = timepicked;
        this.abouttask = abouttask;
    }

    public Plans( String name, String fromdate, String todate, String currenttime, String timepicked, String abouttask) {
        this.name = name;
        this.fromdate = fromdate;
        this.todate = todate;
        this.currenttime = currenttime;
        this.timepicked = timepicked;
        this.abouttask = abouttask;
    }

    public Plans() {
    }


    public int getTaskpending() {
        return taskpending;
    }

    public void setTaskpending(int taskpending) {
        this.taskpending = taskpending;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public String getCurrenttime() {
        return currenttime;
    }

    public void setCurrenttime(String currenttime) {
        this.currenttime = currenttime;
    }

    public String getTimepicked() {
        return timepicked;
    }

    public void setTimepicked(String timepicked) {
        this.timepicked = timepicked;
    }

    public String getAbouttask() {
        return abouttask;
    }

    public void setAbouttask(String abouttask) {
        this.abouttask = abouttask;
    }
}
