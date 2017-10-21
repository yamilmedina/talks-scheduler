package com.example.confmanager;

import com.example.confmanager.misc.SchedulerException;
import com.example.confmanager.model.Session;
import com.example.confmanager.model.Talk;
import java.time.LocalTime;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

public class SessionTest {

    @Test
    public void addTalk_noDeberiaInsertar() throws SchedulerException {
        Session session = new Session("TEST");
        boolean addTalk = session.addTalk(null);
        Assert.assertFalse("Inserto charla", addTalk);
    }

    @Test
    public void addTalk_deberiaInsertar() throws SchedulerException {
        Session session = new Session("TEST");
        boolean addTalk = session.addTalk(Talk.LUNCH);
        Assert.assertTrue("No inserto charla", addTalk);
    }

    @Test
    public void addTalk_deberiaInsertarOrdenandoPorFecha() throws SchedulerException {
        Session session = new Session("TEST");
        boolean addTalkNetw = session.addTalk(Talk.NETWORKING_EVENT);
        boolean addTalkLuch = session.addTalk(Talk.LUNCH);
        Talk last = session.getOrderedTalks().last();
        Assert.assertThat(Talk.NETWORKING_EVENT, Is.is(last));
    }

    @Test
    public void addTalk_deberiaValidarNoSobrepasoMorning() throws SchedulerException {
        Session session = new Session("Morning session");
        Talk talkA = new Talk("Talk A", 60);
        Talk talkB = new Talk("Talk B", 60);
        Talk talkC = new Talk("Talk C", 60);
        Talk talkD = new Talk("Talk D", 30);
        session.addTalk(talkA);
        session.addTalk(talkB);
        session.addTalk(talkC);
        session.addTalk(talkD);
        Assert.assertThat(talkA.getStartTime(), Is.is(LocalTime.of(9, 0)));
        Assert.assertThat(talkB.getStartTime(), Is.is(LocalTime.of(10, 5)));
        Assert.assertThat(talkC.getStartTime(), Is.is(LocalTime.of(13, 0)));
        Assert.assertThat(talkD.getStartTime(), Is.is(LocalTime.of(11, 10)));
    }

}
