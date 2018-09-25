package com.grubBuddy.ingest;

import com.grubBuddy.ingest.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@Component
public class ApplicationStartup implements
        ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    FoodService foodService;

    private static final Logger LOG
            = Logger.getLogger(ApplicationStartup.class.getName());

    public static int counter;

    @Override public void onApplicationEvent(ApplicationReadyEvent event) {
            CompletableFuture.runAsync(() -> {
                try {
                    this.foodService.syncFoodsDatabase();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
    }
}
