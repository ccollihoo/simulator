package de.codecentric.simulation;


import java.util.Comparator;

public class JobComparator implements Comparator<Task> {
    public int compare(Task task1, Task task2) {
        int answer;
        float progress1 = task1.getProgress();
        float progress2 = task2.getProgress();
        if ( progress1 == progress2) {
            answer = 0;
        } else if (progress1 > progress2) {
            answer = 1;
        } else {
            answer = -1;
        }
        return -1 * answer;
    }
}

