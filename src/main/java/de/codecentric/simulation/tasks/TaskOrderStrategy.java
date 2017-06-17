package de.codecentric.simulation.tasks;

import java.util.List;


public interface TaskOrderStrategy {

    List<Task> sortTasks(List<Task> tasks);

}
