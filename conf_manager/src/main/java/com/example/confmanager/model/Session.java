package com.example.confmanager.model;

import java.time.LocalTime;
import java.util.SortedSet;
import java.util.TreeSet;

public class Session {

    private String theme;
    private int durationAM = Moment.MORNING.getTotalMinutes();
    private int durationPM = Moment.AFTERNOON.getTotalMinutes();
    private SortedSet<Talk> orderedTalks;

    public Session(String theme) {
        this.theme = theme;
        this.orderedTalks = new TreeSet<>((t1, t2) -> t1.getStartTime().compareTo(t2.getStartTime()));
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public int getDurationAM() {
        return durationAM;
    }

    public int getDurationPM() {
        return durationPM;
    }

    public void setDurationAM(int durationAM) {
        this.durationAM = durationAM;
    }

    public void setDurationPM(int durationPM) {
        this.durationPM = durationPM;
    }

    public SortedSet<Talk> getOrderedTalks() {
        return orderedTalks;
    }

    public boolean addTalk(Talk talk) {
        boolean added = false;
        if (talk != null && talk.getStartTime() != null) {
            orderedTalks.add(talk);
            return true;
        }

        if (talk != null) {
            if (orderedTalks.isEmpty()) {
                talk.setStartTime(LocalTime.of(9, 0));
                talk.setMoment(Moment.MORNING);
                durationAM -= talk.getDurationMinutes();
            } else {
                Talk lastTalk;
                if (durationAM - talk.getDurationMinutes() >= 0) {
                    lastTalk = orderedTalks.last();
                    LocalTime nextStartTime = lastTalk.getStartTime().plusMinutes(lastTalk.getDurationMinutes());
                    talk.setStartTime(nextStartTime);
                    talk.setMoment(Moment.MORNING);
                    durationAM -= talk.getDurationMinutes();
                } else if (durationPM - talk.getDurationMinutes() >= 0) {
                    orderedTalks.add(Talk.LUNCH);
                    lastTalk = orderedTalks.last();
                    LocalTime nextStartTime = lastTalk.getStartTime().plusMinutes(lastTalk.getDurationMinutes());
                    talk.setStartTime(nextStartTime);
                    talk.setMoment(Moment.AFTERNOON);
                    durationPM -= talk.getDurationMinutes();
                } else {
                    return false;
                }
            }
            orderedTalks.add(talk);
            added = true;
        }
        return added;
    }
}
