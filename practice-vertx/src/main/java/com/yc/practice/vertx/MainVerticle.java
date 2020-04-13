package com.yc.practice.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBusOptions;

import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start() {
        vertx.createHttpServer()
                .requestHandler(req -> req.response().end("Hello Vert.x!"))
                .listen(8080);
    }

    public static void main(String[] args) {
        runExample("practice-vertx/src/main/java" + MainVerticle.class.getPackage().getName().replace(".", "/"),
                MainVerticle.class.getName(), new VertxOptions().setEventBusOptions(new EventBusOptions().setClustered(false)), null);
    }

    public static void runExample(String exampleDir, String verticleID,
                                  VertxOptions options, DeploymentOptions deploymentOptions) {
        if (options == null) {
            options = new VertxOptions();
        }
        try {
            File current = new File(".").getCanonicalFile();
            if (exampleDir.startsWith(current.getName()) && !exampleDir.equals(current.getName())) {
                exampleDir = exampleDir.substring(current.getName().length() + 1);
            }
        } catch (IOException e) {
        }

        System.setProperty("vertx.cwd", exampleDir);
        Consumer<Vertx> runner = vertx -> {
            try {
                if (deploymentOptions != null) {
                    vertx.deployVerticle(verticleID, deploymentOptions);
                } else {
                    vertx.deployVerticle(verticleID);
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        };
        if (options.getEventBusOptions().isClustered()) {
            Vertx.clusteredVertx(options, res -> {
                if (res.succeeded()) {
                    Vertx vertx = res.result();
                    runner.accept(vertx);
                } else {
                    res.cause().printStackTrace();
                }
            });
        } else {
            Vertx vertx = Vertx.vertx(options);
            runner.accept(vertx);
        }
    }
}
