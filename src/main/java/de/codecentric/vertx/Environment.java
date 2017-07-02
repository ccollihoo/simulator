package de.codecentric.vertx;

import de.codecentric.simulation.Employee;
import de.codecentric.simulation.tasks.ParallelizeTaskStrategy;
import de.codecentric.simulation.tasks.Task;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Environment {
    private static final Random rand = new Random();

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        EventBus eventBus = vertx.eventBus();

        Employee employee = new Employee(new ParallelizeTaskStrategy());

        DeploymentOptions options = new DeploymentOptions().setWorker(true);
        vertx.deployVerticle(employee, options, res -> {
            if (res.succeeded()) {
                vertx.setPeriodic(5000, id -> {
                    eventBus.publish("task.new.work", generateTaskMessage().toString());
                });
                vertx.setPeriodic(10000, id -> {
                    eventBus.publish("employee.happinessIndex", "{}");
                });

                vertx.setPeriodic(1000, id -> {
                    eventBus.publish("task.work.iterate", "Do iteration");
                });



            } else {
                System.out.println("failed");
            }
        });
    }

    private static JsonObject generateTaskMessage() {
        Map<String, Object> map = new HashMap<>();
        int duration = rand.nextInt(Employee.ITERATIONS_PER_PERIOD) + 1;
        map.put("duration", duration);
        return new JsonObject(map);
    }
}
