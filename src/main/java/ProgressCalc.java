import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ProgressCalc {

    private final int totalMinutes = 82 * 60;
    private int currentMinutes;
    private int leftMinutes = totalMinutes;

    public void addMinutes(int minutes) {
        currentMinutes += minutes;
        leftMinutes -= minutes;
        System.out.println(this);
    }

    private String normalizeCurrentTime() {
        return "Просмотрено: " + currentMinutes / 60 + ":" + currentMinutes % 60;
    }

    private String normalizeLeftTime() {
        return "Осталось: " + leftMinutes / 60 + ":" + leftMinutes % 60;
    }

    @Override
    public String toString() {
        return normalizeCurrentTime() + "\n" + normalizeLeftTime();
    }
}


class Test {
    public static void main(String[] args) {
        ProgressCalc calc = new ProgressCalc();
        System.out.println("-----------------------");
        calc.addMinutes(2140); // 1-14 lessons
        System.out.println("-----------------------");
        System.out.println("-----------------------");
        calc.addMinutes(120); // 15 lessons
        System.out.println("-----------------------");
        calc.addMinutes(100); // 16 lessons
        System.out.println("-----------------------");
        calc.addMinutes(135); // 17 lessons
        System.out.println("-----------------------");
    }
}