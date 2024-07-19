package BankDto;

import java.math.BigDecimal;

public class MonoRate implements   Bank{

    private long currencyCodeA;
   private  long currencyCodeB;
    private long date;



    public MonoRate(){

    }

    public MonoRate(long currencyCodeA, long currencyCodeB, long date) {
        this.currencyCodeA = currencyCodeA;
        this.currencyCodeB = currencyCodeB;
        this.date = date;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getCurrencyCodeA() {
        return currencyCodeA;
    }

    public void setCurrencyCodeA(long currencyCodeA) {
        this.currencyCodeA = currencyCodeA;
    }

    public long getCurrencyCodeB() {
        return currencyCodeB;
    }

    public void setCurrencyCodeB(long currencyCodeB) {
        this.currencyCodeB = currencyCodeB;
    }

    @Override
    public BigDecimal setDollar() {
        return null;
    }

    @Override
    public BigDecimal setEuro() {
        return null;
    }
}
