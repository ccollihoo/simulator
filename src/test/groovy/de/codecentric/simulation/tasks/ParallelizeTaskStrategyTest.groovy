package de.codecentric.simulation.tasks

import de.codecentric.simulation.Task
import spock.lang.Specification


class ParallelizeTaskStrategyTest extends Specification {

    def "an empty list is returned empty" () {
        TaskOrderStrategy strategy = new ParallelizeTaskStrategy();
        List<Task> unorderedTasks = new ArrayList<>();

        when:
        List<Task> orderedTasks = strategy.sortTasks(unorderedTasks);

        then:
        orderedTasks ==  Collections.emptyList()
    }

    def "When one job has more progress, it is handled later" () {
        TaskOrderStrategy strategy = new ParallelizeTaskStrategy();
        List<Task> unorderedTasks = new ArrayList<>();
        Task taskOne = new Task(2);
        taskOne.workOnJob();
        Task taskTwo = new Task(2);
        unorderedTasks.add(taskOne);
        unorderedTasks.add(taskTwo);

        when:
        List<Task> orderedTasks = strategy.sortTasks(unorderedTasks);

        then:
        orderedTasks.get(0) == taskTwo
        orderedTasks.get(1) == taskOne

    }

    def "When one job with same progress takes more time, it is handled later" () {
        TaskOrderStrategy strategy = new ParallelizeTaskStrategy();
        List<Task> unorderedTasks = new ArrayList<>();
        Task taskOne = new Task(4);
        Task taskTwo = new Task(2);
        unorderedTasks.add(taskOne);
        unorderedTasks.add(taskTwo);

        when:
        List<Task> orderedTasks = strategy.sortTasks(unorderedTasks);

        then:
        orderedTasks.get(0) == taskTwo
        orderedTasks.get(1) == taskOne
    }


}
