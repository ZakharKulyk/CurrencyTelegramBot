package CurrencySort;

import BankParceDto.PrivatDto;
import Parce.PrivatParcer;
import UserConfiguration.UserConfig;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CurrencySort {
    private BigDecimal PrivateBuyUsd;
    private BigDecimal PrivatSellUsd;

    public void SortBuySalePrivatUsdValue()
    {
        PrivatParcer privatParcer = new PrivatParcer();
        UserConfig userConfig = new UserConfig();
        List<PrivatDto> rates = privatParcer.getRequest();

        Optional<PrivatDto> usdRate = rates.stream()
                .filter(dto->dto.ccy().equalsIgnoreCase("USD")&& dto.base_ccy().equalsIgnoreCase("UAH"))
                .findFirst();

        usdRate.ifPresent(dto-> {
            PrivateBuyUsd = dto.buy().setScale(userConfig.getDecimal(), BigDecimal.ROUND_HALF_UP);
            PrivatSellUsd = dto.sale().setScale(userConfig.getDecimal(), BigDecimal.ROUND_HALF_UP);
        });
    }

    public BigDecimal getPrivateBuyUsd() {
        return PrivateBuyUsd;
    }
    public BigDecimal getPrivatSellUsd() {
        return PrivatSellUsd;
    }
}
