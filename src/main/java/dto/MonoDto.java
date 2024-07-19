package dto;

import java.math.BigDecimal;

public record MonoDto(Long currencyCodeA , Long currencyCodeB , Long date,
                      BigDecimal rateSell , BigDecimal rateBuy, BigDecimal rateCross) {
    public long getCurrencyCodeA() {
        return currencyCodeA;
    }
    public long getCurrencyCodeB() {
        return currencyCodeB;
    }
    public long getDate() {
        return date;
    }
}
