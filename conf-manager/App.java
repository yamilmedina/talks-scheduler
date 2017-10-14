
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    final static String VALID_INPUT = "^[^0-9^\\n]+(?:[0-9]*min|lightning)$";
    final static String TITLE = "((\\d)+min*|lightning)";
    final static String TIME = "^([^0-9^\\n]*)(?=([0-9]+min|lightning)$)";

    public static void main(String[] args) {
        String workingDir = System.getProperty("user.dir");
        try (Stream<String> stream = Files.lines(Paths.get(workingDir + File.separator + args[0]))) {
            LinkedList<Talk> talksPool = stream.filter(l -> l.matches(VALID_INPUT))
                    .map(l -> {
                        String title = l.split(TITLE)[0];
                        String time = l.split(TIME)[1];
                        int minutes = "lightning".equalsIgnoreCase(time.trim()) ? 5 : Integer.parseInt(time.replaceAll("min", ""));
                        return new Talk(title, minutes);
                    }).collect(Collectors.toCollection(LinkedList::new));

            TalkScheduler talkScheduler = new TalkScheduler(talksPool);
            talkScheduler.schedule();
            talkScheduler.print();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class TalkScheduler {

        LinkedList<Talk> talksPool;
        Session session1 = new Session("Tematica 1");
        Session session2 = new Session("Tematica 2");

        public TalkScheduler(LinkedList<Talk> talksPool) {
            this.talksPool = talksPool;
        }

        public void schedule() {
            for (Talk t : talksPool) {
                boolean addTalk = session1.addTalk(t);
                if (!addTalk) {
                    session2.addTalk(t);
                }
            }
        }

        public void print() {
            System.out.println(session1.getTheme());
            session1.getTalks().forEach(System.out::println);
            System.out.println(session2.getTheme());
            session2.getTalks().forEach(System.out::println);
        }

    }

    static class Session {

        private String theme;
        private int durationAM = MOMENT.MORNING.getTotalMinutes();
        private int durationPM = MOMENT.AFTERNOON.getTotalMinutes();
        private LinkedList<Talk> talks;

        public Session(String theme) {
            this.theme = theme;
            this.talks = new LinkedList<>();
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public List<Talk> getTalks() {
            return talks;
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

        public boolean addTalk(Talk talk) {
            boolean added = false;
            if (talk != null) {
                if (talks.isEmpty()) {
                    talk.setStartTime(LocalTime.of(9, 0));
                    talk.setMoment(MOMENT.MORNING);
                    durationAM -= talk.getDurationMinutes();
                } else {
                    Talk lastTalk = talks.getFirst();
                    if (durationAM - talk.getDurationMinutes() >= 0) {
                        LocalTime nextStartTime = lastTalk.getStartTime().plusMinutes(lastTalk.durationMinutes);
                        talk.setStartTime(nextStartTime);
                        talk.setMoment(MOMENT.MORNING);
                        durationAM -= talk.getDurationMinutes();
                    } else if (durationPM - talk.getDurationMinutes() >= 0) {
                        //todo: verificar comienzo session tarde....
                        LocalTime nextStartTime = lastTalk.getStartTime().plusMinutes(lastTalk.durationMinutes);
                        talk.setStartTime(nextStartTime);
                        talk.setMoment(MOMENT.AFTERNOON);
                        durationPM -= talk.getDurationMinutes();
                    } else {
                        return false;
                    }
                }
                talks.push(talk);
                added = true;
            }
            return added;
        }

    }

    enum MOMENT {

        MORNING(180),
        AFTERNOON(240);
        private final int totalMinutes;

        private MOMENT(int minutes) {
            this.totalMinutes = minutes;
        }

        public int getTotalMinutes() {
            return totalMinutes;
        }

    };

    static class Talk {

        public final static Talk LUNCH = new Talk("Lunch", 60, LocalTime.of(12, 0));
        public final static Talk NETWORKING_EVENT = new Talk("Networking Event", 60, LocalTime.of(17, 0));

        private String name;
        private MOMENT moment;
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

        public MOMENT getMoment() {
            return moment;
        }

        public void setMoment(MOMENT moment) {
            this.moment = moment;
        }

        @Override
        public String toString() {
            return startTime.format(DateTimeFormatter.ofPattern("HH:mma")) + " " + name + " " + durationMinutes + "min";
        }

    }
}
