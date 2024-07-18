package UserConfiguration;

import BankDto.Bank;
import BankDto.MonoRate;
import BankDto.NBURate;
import BankDto.PrivatRate;

import java.math.BigDecimal;
import java.util.HashMap;

public class UserConfig {
    MonoRate monoRate = new MonoRate();
    NBURate nbuRate = new NBURate();
    PrivatRate privatRate = new PrivatRate();


    HashMap<Bank, BigDecimal>bankCurrencyMap  = new HashMap<>();

    public UserConfig(){
        bankCurrencyMap.put(privatRate,privatRate.setDollar());
    }
}
