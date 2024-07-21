package UserConfiguration;

import dto.Bank;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserConfig {
    List<String> bankList = new ArrayList<>();
    List<BigDecimal> currentCurrencies = new ArrayList<>();

    public void addBank(String name){
        bankList.add(name);
    }
    public void removeBank(String name){
        bankList.remove(name);
    }
    public List<String> getBanks(){
        return bankList;
    }
}
