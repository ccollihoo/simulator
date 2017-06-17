package de.codecentric.simulation

import spock.lang.Specification

class TaskTest extends Specification {

    def "Progress is correctly calculated" () {
        Task task = new Task(4)

        when:
        task.workOnJob()

        then:
        task.getProgress() == 1/4
    }

}
