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

    def "When one job takes more time, it is handled later" () {
        TaskOrderStrategy strategy = new ParallelizeTaskStrategy();
        List<Task> unorderedTasks = new ArrayList<>();
        unorderedTasks.add(new Task(4));
        unorderedTasks.add(new Task(2));

        when:
        List<Task> orderedTasks = strategy.sortTasks(unorderedTasks);

        then:
        orderedTasks.get(0).duration == 2
        orderedTasks.get(1).duration == 4

    }


}
