package BankParceDto;

import java.math.BigDecimal;

public record MonoDto(Long currencyCodeA , Long currencyCodeB , Long date,
                      BigDecimal rateSell , BigDecimal rateBuy, BigDecimal rateCross) implements BankDto {
}
