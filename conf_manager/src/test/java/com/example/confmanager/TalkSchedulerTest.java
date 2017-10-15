package com.example.confmanager;

import com.example.confmanager.misc.SchedulerException;
import com.example.confmanager.scheduler.TalkScheduler;
import java.util.ArrayList;
import org.junit.Test;
import org.mockito.Mockito;

public class TalkSchedulerTest {

    @Test(expected = SchedulerException.class)
    public void iniciacionClase_deberiaFallar() throws SchedulerException {
        TalkScheduler talkScheduler = new TalkScheduler(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void usoPrintArchivo_deberiaFallar() throws SchedulerException {
        TalkScheduler talkScheduler = new TalkScheduler(Mockito.mock(ArrayList.class));
        talkScheduler.print("/tmp");
    }

}
