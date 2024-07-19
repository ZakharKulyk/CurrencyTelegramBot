package dto;

import java.math.BigDecimal;

public record PrivatDto(String ccy, String base_ccy, BigDecimal buy, BigDecimal sale) {
}
