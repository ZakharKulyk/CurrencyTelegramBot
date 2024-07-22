package CurrencySort;

import Parce.MonoParcer;
import Parce.NBUParcer;
import Parce.PrivatParcer;
import UserConfiguration.UserConfig;
import dto.PrivatDto;
import org.telegram.telegrambots.meta.api.objects.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CurrencySort {
    private BigDecimal PrivateBuyUsd;
    private BigDecimal PrivatSellUsd;

    public void SortBuySalePrivatUsdValue(UserConfig userConfig) {
        PrivatParcer privatParcer = new PrivatParcer();
        List<PrivatDto> rates = privatParcer.getRequest();

        Optional<PrivatDto> usdRate = rates.stream()
                .filter(dto->dto.getCcy().equalsIgnoreCase("USD")&& dto.getBase_ccy().equalsIgnoreCase("UAH"))
                .findFirst();

        usdRate.ifPresent(dto-> {
            PrivateBuyUsd = dto.getBuy().setScale(userConfig.getDecimal(), BigDecimal.ROUND_HALF_UP);
            PrivatSellUsd = dto.getSale().setScale(userConfig.getDecimal(), BigDecimal.ROUND_HALF_UP);
        });
    }

    public BigDecimal getPrivateBuyUsd() {
        return PrivateBuyUsd;
    }

    public BigDecimal getPrivatSellUsd() {
        return PrivatSellUsd;
    }

}

