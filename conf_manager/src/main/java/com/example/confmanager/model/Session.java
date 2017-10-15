package com.example.confmanager.model;

import java.time.LocalTime;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Representacion de una tematica de la conferencia, la cual contiene una lista
 * de temas separadas segun la jornada que le corresponda AM/PM.
 *
 * @author yamil
 */
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

    /**
     * Permite agregar un tema a la lista, realizando validacion de no
     * "overlaping" de tema y con las restricciones dadas de almuerzo 12 hrs y
     * evento social 17 hrs.
     *
     * @param talk
     * @return
     */
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
