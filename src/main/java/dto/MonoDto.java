package dto;

import java.math.BigDecimal;

public record MonoDto(Long currencyCodeA , Long currencyCodeB , Long date,
                      BigDecimal rateSell , BigDecimal rateBuy, BigDecimal rateCross)implements Bank {
    public long getCurrencyCodeA() {
        return currencyCodeA;
    }
    public long getCurrencyCodeB() {
        return currencyCodeB;
    }
    public long getDate() {
        return date;
    }

    @Override
    public BigDecimal setDollar() {
        return null;
    }

    @Override
    public BigDecimal setEuro() {
        return null;
    }

    @Override
    public String setName() {
        return null;
    }
}
