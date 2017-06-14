package de.codecentric.simulation;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class Employee {
    public static final int ITERATIONS_PER_PERIOD = 8;

    private List<Task> tasks;

    public Employee () {
        this.tasks = new ArrayList<>();
    }

    public void takeJob(Task task) {
        tasks.add(task);
    }

    public void worksOnJobs() {
        for (int i = 0; i < ITERATIONS_PER_PERIOD; i++) {
            List<Task> orderedTasks = tasks.stream().sorted(new JobComparator()).collect(Collectors.toList());
            if (! orderedTasks.isEmpty()) {
                Task currentTask = orderedTasks.get(0);
                currentTask.workOnJob();
                if (currentTask.isDone()) {
                    tasks.remove(currentTask);
                }
            }
        }
    }

  private class JobComparator implements Comparator<Task> {
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
}
