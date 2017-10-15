package com.example.confmanager.scheduler;

import com.example.confmanager.model.Session;
import com.example.confmanager.model.Talk;
import java.util.List;

public class TalkScheduler {

    private final List<Talk> talksPool;
    private Session session1 = new Session("Tematica 1");
    private Session session2 = new Session("Tematica 2");

    public TalkScheduler(List<Talk> talksPool) {
        this.talksPool = talksPool;
    }

    public void schedule() {
        for (Talk t : talksPool) {
            boolean added = session1.addTalk(t);
            if (!added) {
                session2.addTalk(t);
            }
        }
        session1.addTalk(Talk.NETWORKING_EVENT);
        session2.addTalk(Talk.NETWORKING_EVENT);
    }

    public void print() {
        System.out.println(session1.getTheme());
        session1.getOrderedTalks().forEach(System.out::println);
        System.out.println(session2.getTheme());
        session2.getOrderedTalks().forEach(System.out::println);
    }
}
