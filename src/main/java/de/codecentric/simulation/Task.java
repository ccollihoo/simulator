package de.codecentric.simulation;


import java.math.BigDecimal;

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
        return new BigDecimal(workDone).divide(new BigDecimal(duration));
    }

    public boolean isDone() {
        return workDone >= duration;
    }

    public String getDescription() {
        return this + " - Duration: " + duration + "; already done: " + workDone;
    }

}
