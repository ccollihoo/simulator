package de.codecentric.simulation.tasks;


import de.codecentric.simulation.Task;

import java.util.List;
import java.util.stream.Collectors;

public class ParallelizeTaskStrategy {

    public List<Task> sortTasks(List<Task> tasks) {
        return tasks.stream().sorted(new JobComparator()).collect(Collectors.toList());
    }

}
