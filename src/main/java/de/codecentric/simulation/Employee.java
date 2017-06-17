package de.codecentric.simulation;

import de.codecentric.simulation.tasks.JobComparator;
import de.codecentric.simulation.tasks.ParallelizeTaskStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Employee {
    public static final int ITERATIONS_PER_PERIOD = 8;
    private final ParallelizeTaskStrategy jobOrderStrategy;
    private List<Task> tasks;
    private int happinessIndex;

    public Employee(ParallelizeTaskStrategy jobOrderStrategy) {
        this.tasks = new ArrayList<>();
        this.happinessIndex = 10;
        this.jobOrderStrategy = jobOrderStrategy;
    }

    public void takeJob(Task task) {
        tasks.add(task);
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public void worksOnJobs() {
        for (int i = 0; i < ITERATIONS_PER_PERIOD; i++) {
            List<Task> orderedTasks = jobOrderStrategy.sortTasks(tasks);
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