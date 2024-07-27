package dto;

import java.math.BigDecimal;

import static сonstants.ConstantForDevProcess.PRIVAT_BANK;

public class PrivatDto implements Bank {
    private String ccy;
    private String base_ccy;
    private BigDecimal buy;
    private BigDecimal sale;


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

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getSale() {
        return sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }

    @Override
    public String getName() {
        return PRIVAT_BANK;
    }
}
