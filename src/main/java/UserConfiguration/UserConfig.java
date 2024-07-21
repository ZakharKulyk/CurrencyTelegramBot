package UserConfiguration;

import dto.Bank;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserConfig {
    private static int decimal = 2;
    List<Bank> currentBanks = new ArrayList<>();
    List<BigDecimal> currentCurrencies = new ArrayList<>();

    public void setDecimal(int decimal)
    {
        this.decimal = decimal;
    }

    public int getDecimal()
    {
        return decimal;
    }
}
