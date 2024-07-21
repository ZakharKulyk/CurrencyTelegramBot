package UserConfiguration;


import BankParceDto.BankDto;
import BankParceDto.MonoDto;
import BankParceDto.NbuDto;
import BankParceDto.PrivatDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;

public class UserConfig {

    private static int decimal ;

    HashMap<BankDto, BigDecimal>bankCurrencyMap  = new HashMap<>();

    public UserConfig(){

    }

    public int getDecimal() {
        return decimal;
    }
    public void setDecimal(int decimal) {
        this.decimal = decimal;
    }

}
