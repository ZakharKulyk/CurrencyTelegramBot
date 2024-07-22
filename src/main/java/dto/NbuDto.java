package dto;

import java.math.BigDecimal;

import static Constants.ConstansDev.NBU_BANK;

public class NbuDto implements  Bank{
    Long r030;
    String txt;
    BigDecimal rate;
    String cc;
    String exchangedate;

    public Long getR030() {
        return r030;
    }

    public void setR030(Long r030) {
        this.r030 = r030;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(String exchangedate) {
        this.exchangedate = exchangedate;
    }

    @Override
    public String getName() {
        return NBU_BANK;
    }
}