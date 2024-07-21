package UserConfiguration;

import dto.Bank;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class UserConfig {
    LocalTime currentTime;
    LocalTime timeForNotification;
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    ScheduledFuture<?> scheduledFuture;

    List<String> currentBanks = new ArrayList<>();
    List<BigDecimal> currentCurrencies = new ArrayList<>();

    public LocalTime getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(LocalTime currentTime) {
        this.currentTime = currentTime;
    }

    public LocalTime getTimeForNotification() {
        return timeForNotification;
    }

    public void setTimeForNotification(LocalTime timeForNotification) {
        this.timeForNotification = timeForNotification;
    }

    public ScheduledExecutorService getService() {
        return service;
    }

    public void setService(ScheduledExecutorService service) {
        this.service = service;
    }

    public UserConfig() {}

    public long calculateDelay() {
        currentTime = LocalTime.now();
        long delay = timeForNotification.toSecondOfDay() - currentTime.toSecondOfDay();
        if (delay < 0) {
            delay += TimeUnit.DAYS.toSeconds(1);
        }
        return delay;
    }

    public void cancelPreviousNotification() {
        if (scheduledFuture != null && !scheduledFuture.isDone()) {
            scheduledFuture.cancel(false);
        }
    }

    public void scheduleNotification(Runnable task, long delay, long period, TimeUnit unit) {
        cancelPreviousNotification();
        scheduledFuture = service.scheduleAtFixedRate(task, delay, period, unit);
    }
}