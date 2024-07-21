package BankParceDto;

import java.math.BigDecimal;

public record NbuDto(Long r030, String txt, BigDecimal rate , String cc , String exchangedate) implements BankDto {

}