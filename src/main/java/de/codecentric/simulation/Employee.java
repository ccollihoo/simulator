package de.codecentric.simulation;

import de.codecentric.simulation.tasks.Task;
import de.codecentric.simulation.tasks.TaskOrderStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Employee {
    public static final int ITERATIONS_PER_PERIOD = 8;
    private final TaskOrderStrategy taskOrderStrategy;
    private final List<Task> tasks;
    private int happinessIndex;

    public Employee(TaskOrderStrategy taskOrderStrategy) {
        this.tasks = new ArrayList<>();
        this.happinessIndex = 10;
        this.taskOrderStrategy = taskOrderStrategy;
    }

    public void takeJob(Task task) {
        tasks.add(task);
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public void worksOnJobs() {
        for (int i = 0; i < ITERATIONS_PER_PERIOD; i++) {
            List<Task> orderedTasks = taskOrderStrategy.sortTasks(tasks);
            if (!orderedTasks.isEmpty()) {
                Task currentTask = orderedTasks.get(0);
                currentTask.workOnJob();
                if (currentTask.isDone()) {
                    tasks.remove(currentTask);
                }
            }
        }
        checkHappinessIndex();
    }

    public int getHappinessIndex() {
        return happinessIndex;
    }

    private void checkHappinessIndex() {
        List jobsNotDone = tasks.stream().filter(task -> ! task.isDone()).collect(Collectors.toList());

        if (! jobsNotDone.isEmpty()) {
            happinessIndex--;
        }
    }
}