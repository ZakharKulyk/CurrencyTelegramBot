package BankDto;

import java.math.BigDecimal;

public class PrivatRate implements Bank {

    private String ccy;
    private String base_ccy;
    private double buy;
    private double sale;

    // Конструкторы
    public PrivatRate() {
    }

    public PrivatRate(String ccy, String base_ccy, double buy, double sale) {
        this.ccy = ccy;
        this.base_ccy = base_ccy;
        this.buy = buy;
        this.sale = sale;
    }

    // Геттеры и сеттеры
    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBase_ccy() {
        return base_ccy;
    }

    public void setBase_ccy(String base_ccy) {
        this.base_ccy = base_ccy;
    }

    public double getBuy() {
        return buy;
    }

    public void setBuy(double buy) {
        this.buy = buy;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "PrivatRate{" +
                "ccy='" + ccy + '\'' +
                ", base_ccy='" + base_ccy + '\'' +
                ", buy=" + buy +
                ", sale=" + sale +
                '}';
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
