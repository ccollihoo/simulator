package de.codecentric.simulation.tasks;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class Task {

    private int duration;

    private int workDone;

    public Task(int duration) {
        this.duration = duration;
        workDone = 0;
    }

    public int getDuration() {
        return duration;
    }

    public void workOnJob() {
        workDone++;
    }

    public BigDecimal getProgress() {
        return new BigDecimal(workDone).divide(new BigDecimal(duration), 2, RoundingMode.HALF_UP);
    }

    public boolean isDone() {
        return workDone >= duration;
    }

    public String getDescription() {
        return this + " - Duration: " + duration + "; already done: " + workDone;
    }

}
