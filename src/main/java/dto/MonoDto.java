package dto;


import java.math.BigDecimal;

public class MonoDto implements  Bank{

    Long currencyCodeA;
    Long currencyCodeB;
    Long date;
    BigDecimal rateSell;
    BigDecimal rateBuy;
    BigDecimal rateCross;


    public long getCurrencyCodeA() {
        return currencyCodeA;
    }
    public long getCurrencyCodeB() {
        return currencyCodeB;
    }
    public long getDate() {
        return date;
    }

    public void setCurrencyCodeA(Long currencyCodeA) {
        this.currencyCodeA = currencyCodeA;
    }

    public void setCurrencyCodeB(Long currencyCodeB) {
        this.currencyCodeB = currencyCodeB;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public BigDecimal getRateSell() {
        return rateSell;
    }

    public void setRateSell(BigDecimal rateSell) {
        this.rateSell = rateSell;
    }

    public BigDecimal getRateBuy() {
        return rateBuy;
    }

    public void setRateBuy(BigDecimal rateBuy) {
        this.rateBuy = rateBuy;
    }

    public BigDecimal getRateCross() {
        return rateCross;
    }

    public void setRateCross(BigDecimal rateCross) {
        this.rateCross = rateCross;
    }
}



