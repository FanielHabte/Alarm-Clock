import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Alarm implements Runnable {
    private String status;
    private final LocalDateTime alarmDateTime;

    Alarm (LocalDateTime alarmDateTime) {
        this.status = "INACTIVE";
        this.alarmDateTime = alarmDateTime;
    }

    @Override
    public void run() {

        while (LocalDateTime.now().isBefore(this.alarmDateTime)) {
            try {
                Thread.sleep(1000);
                System.out.print("\r"+ LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!");;
            }
        }
    }

    void activate () {
        this.status = "ACTIVE";
    }

    String getAlarmStatus () {
        return this.status;
    }

    void deactivate () {
        this.status = "STOPPED";
    }

    String getAlarmDateTime () {
        return this.alarmDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"));
    }

    void getAlarmMessage () {
        switch (this.status.toLowerCase()) {
            case "active" -> System.out.println("\nYour alarm is active and set for " + getAlarmDateTime() + ".");
            case "stopped" -> System.out.println("\nYour alarm has stopped.");
            case "inactive" -> System.out.println("\nYour alarm is inactive.");
        }
    }

}
