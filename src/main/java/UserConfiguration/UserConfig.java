package UserConfiguration;

import dto.Bank;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserConfig {

    private static int decimal = 1;
    List<String> decimalPlaces = new ArrayList<>();
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

    public List<String> getDecimalPlaces()
    {
        return decimalPlaces;
    }
    public void addDigitsAfterDecimalPlace(String digits){
        decimalPlaces.add(digits);
    }
    public void removeDigitsAfterDecimalPlace(String digits){
        decimalPlaces.remove(digits);
    }

    public static void setDecimal(int decimal) {
        UserConfig.decimal = decimal;
    }
    public static int getDecimal() {
        return decimal;
    }

}
