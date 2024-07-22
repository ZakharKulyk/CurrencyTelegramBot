package UserConfiguration;

import Constants.ConstansDev;
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
    private static int decimal = 1;

    LocalTime currentTime;
    List<String> decimalPlaces = new ArrayList<>();
    LocalTime timeForNotification;
    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
    ScheduledFuture<?> scheduledFuture;
    List<String> bankList = new ArrayList<>();
    List<String> currentCurrencies = List.of(ConstansDev.DOLLAR, ConstansDev.EURO);



    public List<String> getCurrentCurrencies() {
        return currentCurrencies;
    }

    public void setCurrentCurrencies(List<String> currentCurrencies) {
        this.currentCurrencies = currentCurrencies;
    }

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

    public  void addCurrency(String currency){
        currentCurrencies.add(currency);
    }
    public  void deleteCurrency(String currency){
        currentCurrencies.remove(currency);
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
    public List<String> getDecimalPlaces()
    {
        return decimalPlaces;
    }

    public void scheduleNotification(Runnable task, long delay, long period, TimeUnit unit) {
        cancelPreviousNotification();
        scheduledFuture = service.scheduleAtFixedRate(task, delay, period, unit);
    }
    public void addDigitsAfterDecimalPlace(String digits){
        decimalPlaces.add(digits);
    }
    public void removeDigitsAfterDecimalPlace(String digits){
        decimalPlaces.remove(digits);
    }

    public  void setDecimal(int decimal) {
        UserConfig.decimal = decimal;
    }

    public  int getDecimal() {
        return decimal;
    }
    public void addBank(String name){
        bankList.add(name);
    }
    public void removeBank(String name){
        bankList.remove(name);
    }
    public List<String> getBanks(){
        return bankList;
    }

    public ScheduledFuture<?> getScheduledFuture() {
        return scheduledFuture;
    }

    public void setScheduledFuture(ScheduledFuture<?> scheduledFuture) {
        this.scheduledFuture = scheduledFuture;
    }
}