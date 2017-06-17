package de.codecentric.simulation.tasks;

import de.codecentric.simulation.Task;

import java.util.List;


public interface TaskOrderStrategy {

    List<Task> sortTasks(List<Task> tasks);

}
