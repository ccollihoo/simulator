package de.codecentric.simulation

import de.codecentric.simulation.tasks.ParallelizeTaskStrategy
import spock.lang.Specification


class EmployeeTest extends Specification {

    private Employee employee

    def setup() {
        employee = new Employee(new ParallelizeTaskStrategy())
    }

    def "Initial happiness index is ten" () {
        expect:
        employee.happinessIndex == 10
    }

    def "Initially there are no tasks for employee" () {
        expect:
        employee.getNumberOfTasks() == 0
    }

    def "When tasks is added, employee reports correct number" () {
        when:
        employee.takeJob(new Task(1))
        then:
        employee.getNumberOfTasks() == 1
    }

    def "When duration of given task fits into ITERATION_PER_PERIOD, job will be removed from list" () {
        employee.takeJob(new Task(duration))

        when:
        employee.worksOnJobs()

        then:
        employee.numberOfTasks == 0

        where:
        duration << (1..Employee.ITERATIONS_PER_PERIOD)
    }

    def "When duration of given task exceeds ITERATION_PER_PERIOD, job will remain in list" () {
        employee.takeJob(new Task(Employee.ITERATIONS_PER_PERIOD + 1))

        when:
        employee.worksOnJobs()

        then:
        employee.numberOfTasks == 1
    }


}
