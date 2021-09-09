package Lesson1;

import java.util.Arrays;

public class Course {
    private String[] carr;

    public void doIt(Team t) {
        System.out.println(t.getTeamName() + " passes course: " + Arrays.toString(carr));
    }

    public Course(String[] carr) {
        this.carr = carr;
    }
}
