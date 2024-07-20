package dto;

import java.math.BigDecimal;

public record PrivatDto(String ccy, String base_ccy, BigDecimal buy, BigDecimal sale)implements  Bank {
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
