package de.codecentric.simulation;


public class Task {

    private int duration;

    public int workDone;

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

    public float getProgress() {
        return workDone/duration;
    }

    public boolean isDone() {
        return workDone >= duration;
    }

    public String getDescription() {
        return this + " - Duration: " + duration + "; already done: " + workDone;
    }

}
