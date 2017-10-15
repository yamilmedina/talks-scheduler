package com.example.confmanager;

import com.example.confmanager.misc.SchedulerException;
import com.example.confmanager.model.Session;
import com.example.confmanager.model.Talk;
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
}
