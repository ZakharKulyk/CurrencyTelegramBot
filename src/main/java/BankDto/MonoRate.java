package BankDto;

import java.math.BigDecimal;

public class MonoRate implements   Bank{

    long currencyCodeA;
    long currencyCodeB;
    long data;



    public MonoRate(){

    }

    public MonoRate(long currencyCodeA, long currencyCodeB, long data) {
        this.currencyCodeA = currencyCodeA;
        this.currencyCodeB = currencyCodeB;
        this.data = data;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
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
