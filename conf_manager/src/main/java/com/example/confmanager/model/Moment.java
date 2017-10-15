package com.example.confmanager.model;

public enum Moment {

    MORNING(180),
    AFTERNOON(240);
    private final int totalMinutes;

    private Moment(int minutes) {
        this.totalMinutes = minutes;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }
}
