package de.codecentric.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by oliverhoogvliet on 14.06.17.
 */
public class Simulator {

    public static final Random rand = new Random();

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        tasks.add(generateJob());
        tasks.add(generateJob());
        tasks.add(generateJob());

        Employee programmer = new Employee();

        for (int period = 0; period < 20; period++) {
            tasks.add(generateJob());

            for (Task currentTask : tasks) {
                if (!currentTask.isDone()) {
                    programmer.takeJob(currentTask);
                }
            }
            programmer.worksOnJobs();

            System.out.println("Result for period " + (period + 1));
            for (Task currentTask : tasks) {
                if (! currentTask.isDone()) {
                    System.out.println("Task " + currentTask.getDescription() + " is NOT done");
                }
            }
            System.out.println("Happiness Index of our programmer: " + programmer.getHappinessIndex());
            if (programmer.getHappinessIndex() <= 0) {
                System.out.println ("PROGRAMMER has resigned; business stops in period " + (period + 1));
                System.exit(0);
            }
        }
    }

    public static Task generateJob() {
        int duration = rand.nextInt(Employee.ITERATIONS_PER_PERIOD) + 1;
        return new Task(duration);
    }


}
