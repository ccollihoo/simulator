package de.codecentric.simulation.tasks;

import de.codecentric.simulation.Task;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ParallelizeTaskStrategy implements TaskOrderStrategy {

    private Comparator<Task> taskComparator = (t1, t2) -> {
        int progressComparation = t1.getProgress().compareTo(t2.getProgress());
        if (progressComparation == 0) {
            return Float.compare(t1.getDuration(), t2.getDuration());
        }
        return progressComparation;
    };

    public List<Task> sortTasks(List<Task> tasks) {
        return tasks.stream().sorted(taskComparator).collect(Collectors.toList());
    }

}
