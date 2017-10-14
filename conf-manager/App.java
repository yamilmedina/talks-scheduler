
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) {
        String workingDir = System.getProperty("user.dir");
        try (Stream<String> stream = Files.lines(Paths.get(workingDir + File.separator + args[0]))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Session {

        private MOMENT moment;
        private final int maxDurationMinutes;
        private List<Talk> talks;

        public Session(MOMENT moment) {
            this.moment = moment;
            this.maxDurationMinutes = moment.getTotalMinutes();
            this.talks = new ArrayList<>();
        }

        public MOMENT getMoment() {
            return moment;
        }

        public void setMoment(MOMENT moment) {
            this.moment = moment;
        }

        public List<Talk> getTalks() {
            return talks;
        }

        public void setTalks(List<Talk> talks) {
            this.talks = talks;
        }

        public boolean addTalk(Talk talk) {
            boolean added = false;
            if (talk != null) {

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

    class Talk {

        private String name;
        private int durationMinutes;
        private Date startDate;

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

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

    }
}
