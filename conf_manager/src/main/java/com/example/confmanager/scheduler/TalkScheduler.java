package com.example.confmanager.scheduler;

import com.example.confmanager.misc.SchedulerException;
import com.example.confmanager.model.Session;
import com.example.confmanager.model.Talk;
import java.util.List;

/**
 * Clase encargada de gestionar sesiones {@link Session} en base a una lista de
 * temas {@link Talk} de la conferencia.
 *
 * @author yamil
 */
public class TalkScheduler {

    private final List<Talk> talksPool;
    private Session session1 = new Session("Tematica 1");
    private Session session2 = new Session("Tematica 2");

    public TalkScheduler(List<Talk> talksPool) throws SchedulerException {
        if (talksPool == null || talksPool.isEmpty()) {
            throw new SchedulerException("No se puede gestionar una conferencia sin temas");
        }
        this.talksPool = talksPool;
    }

    /**
     * Realiza la distribucion de las charlas.
     */
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

    /**
     * Entrega por pantalla el resultado de la calendarizacion de las charlas.
     */
    public void print() {
        System.out.println(session1.getTheme());
        session1.getOrderedTalks().forEach(System.out::println);
        System.out.println(session2.getTheme());
        session2.getOrderedTalks().forEach(System.out::println);
    }

    /**
     * @todo: posible implementacion para dejar resultado en archivo.
     * @param pathToFile
     */
    public void print(String pathToFile) {
        throw new UnsupportedOperationException("No soportado aun.");
    }
}
