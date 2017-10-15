package com.example.confmanager.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Talk {

    public final static Talk LUNCH = new Talk("Lunch", 60, LocalTime.of(12, 0));
    public final static Talk NETWORKING_EVENT = new Talk("Networking Event", 60, LocalTime.of(17, 0));
    
    private String name;
    private Moment moment;
    private LocalTime startTime;
    private int durationMinutes;

    public Talk() {
    }

    public Talk(String name, int durationMinutes) {
        this.name = name;
        this.durationMinutes = durationMinutes;
    }

    public Talk(String name, int durationMinutes, LocalTime startTime) {
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.startTime = startTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Moment getMoment() {
        return moment;
    }

    public void setMoment(Moment moment) {
        this.moment = moment;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Talk other = (Talk) obj;
        return Objects.equals(this.durationMinutes, other.durationMinutes)
                && Objects.equals(this.moment, other.moment)
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.startTime, other.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.durationMinutes, this.moment, this.name, this.startTime);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        if (startTime != null) {
            sb.append(startTime.format(DateTimeFormatter.ofPattern("HH:mma")));
        }
        if (name != null) {
            sb.append(" ");
            sb.append(name);
        }
        if (durationMinutes == 5) {
            sb.append(" lightning");
        } else if (!LUNCH.equals(this) && !NETWORKING_EVENT.equals(this)) {
            sb.append(" ");
            sb.append(durationMinutes);
            sb.append("min");
        }
        return sb.toString();
    }
}
