import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Audio alarmAudio = new Audio("musicFiles/alarm_sound.wav");
        String alarmTimeString = getAlarmDetails();
        LocalDateTime alarmDateTime = covertStringToDateTime(alarmTimeString);

        Alarm alarm = new Alarm(alarmDateTime);
        Thread alarmThread = new Thread(alarm);

        startAlarm(alarm);
        alarmThread.start();

        try {
            alarmThread.join();
            alarmAudio.play();
            stopAlarmSound(alarm);
        }
        catch (InterruptedException e) {
            System.out.println("Something happened!" + e);
        }

    }

    public static String getAlarmDetails () {
        System.out.print("Enter an alarm time (HH:MM:SS): ");
        String timeString = scanner.next();
        System.out.print("Is this AM/PM (AM/PM): ");
        String timePeriod = scanner.next();
        return timeString + " " + timePeriod;
    }

    public static void startAlarm (Alarm alarm) {
        alarm.activate();
        alarm.getAlarmMessage();
    }

    public static void stopAlarmSound (Alarm alarm) {
        System.out.print("Press *Enter* to stop the alarm: ");
        scanner.nextLine();
        String stopAlarm = scanner.nextLine();
        if (stopAlarm.isEmpty()) {
            alarm.deactivate();
            alarm.getAlarmMessage();
        }
    }

    public static LocalDateTime covertStringToDateTime (String userTmeStringInput) {
        String alarmDateTime = LocalDate.now() + " " + userTmeStringInput;
        return LocalDateTime.parse(alarmDateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"));
    }

}