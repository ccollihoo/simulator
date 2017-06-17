package de.codecentric.simulation.tasks;

import de.codecentric.simulation.Task;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelizeTaskStrategy implements TaskOrderStrategy {

    Comparator<Task> byEmployeeNumber = (t1, t2) -> Float.compare(t1.getDuration(), t2.getDuration());

    public List<Task> sortTasks(List<Task> tasks) {
        return tasks.stream().sorted(byEmployeeNumber).collect(Collectors.toList());
    }

}
