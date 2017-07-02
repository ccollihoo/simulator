package de.codecentric.simulation;

import de.codecentric.simulation.tasks.Task;
import de.codecentric.simulation.tasks.TaskOrderStrategy;
import io.vertx.core.*;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Employee extends AbstractVerticle {
    public static final int ITERATIONS_PER_PERIOD = 8;
    private final TaskOrderStrategy taskOrderStrategy;
    private final List<Task> tasks;
    private int happinessIndex;

    public Employee(TaskOrderStrategy taskOrderStrategy) {
        this.tasks = new ArrayList<>();
        this.happinessIndex = 10;
        this.taskOrderStrategy = taskOrderStrategy;
    }

    public void start() {
        EventBus eb = vertx.eventBus();
        eb.consumer("task.new.work", message -> {
            System.out.println (message.body());
            JsonObject taskDescription = new JsonObject(message.body().toString());
            Task task = new Task (taskDescription.getInteger("duration"));
            takeJob(task);
        });
        eb.consumer("task.work.iterate", message -> {
            doOneJobIteration();
        });
        eb.consumer("employee.happinessIndex", message -> {
            checkHappinessIndex();
            System.out.println("Happiness Index: " + happinessIndex);
        });

    }

    public void stop() {

    }

    public void takeJob(Task task) {
        tasks.add(task);
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public void worksOnJobs() {
        for (int i = 0; i < ITERATIONS_PER_PERIOD; i++) {
            doOneJobIteration();
        }
        checkHappinessIndex();
    }

    private void doOneJobIteration() {
        List<Task> orderedTasks = taskOrderStrategy.sortTasks(tasks);
        if (!orderedTasks.isEmpty()) {
            Task currentTask = orderedTasks.get(0);
            currentTask.workOnJob();
            if (currentTask.isDone()) {
                tasks.remove(currentTask);
            }
        } else {
            System.out.println("No tasks to be done");
        }
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