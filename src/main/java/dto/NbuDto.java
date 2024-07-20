package dto;

import java.math.BigDecimal;

public record NbuDto(Long r030, String txt, BigDecimal rate , String cc , String exchangedate)implements Bank{

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