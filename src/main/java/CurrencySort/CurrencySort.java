package CurrencySort;
import Parce.MonoParcer;
import Parce.NBUParcer;
import Parce.PrivatParcer;
import UserConfiguration.UserConfig;
import dto.Bank;
import dto.MonoDto;
import dto.NbuDto;
import dto.PrivatDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static Constants.ConstansDev.*;

public class CurrencySort {
    private BigDecimal PrivateBuyUsd;
    private BigDecimal PrivatSellUsd;
    private  BigDecimal PrivatBuyEuro;
    private  BigDecimal PrivatSellEuro;

    private  BigDecimal MonoBuyUsd;
    private  BigDecimal MonoSellUsd;
    private BigDecimal MonoBuyEuro;
    private  BigDecimal MonoSellEuro;

    private  BigDecimal NbuBuyUsd;
    private BigDecimal NbuSellUsd;
    private BigDecimal NbuBuyEuro;
    private BigDecimal NbuSellEuro;

    public void SortBuySalePrivatUsdValue(UserConfig userConfig)
    {
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

    public  void  getInfoCurrency(UserConfig userConfig){
        if(userConfig.getBanks().contains(PRIVAT_BANK)){
            PrivatParcer privatParcer = new PrivatParcer();
            List<PrivatDto> rates = privatParcer.getRequest();

            Optional<PrivatDto> usdRate = rates.stream()
                    .filter(dto->dto.getCcy().equalsIgnoreCase(DOLLAR)&& dto.getBase_ccy().equalsIgnoreCase(HRYVNA))
                    .findFirst();
            Optional<PrivatDto> eurRate = rates.stream()
                    .filter(dto -> dto.getCcy().equalsIgnoreCase(EURO) && dto.getBase_ccy().equalsIgnoreCase(HRYVNA))
                    .findFirst();

            usdRate.ifPresent(dto-> {
                PrivateBuyUsd = dto.getBuy().setScale(userConfig.getDecimal(), BigDecimal.ROUND_HALF_UP);
                PrivatSellUsd = dto.getSale().setScale(userConfig.getDecimal(), BigDecimal.ROUND_HALF_UP);
            });
            eurRate.ifPresent(dto->{
                PrivatBuyEuro = dto.getBuy().setScale(userConfig.getDecimal(), BigDecimal.ROUND_HALF_UP);
                PrivatSellEuro = dto.getSale().setScale(userConfig.getDecimal(), BigDecimal.ROUND_HALF_UP);
            });

        }
        if(userConfig.getBanks().contains(NBU_BANK)){
            NBUParcer nbuParcer = new NBUParcer();
            List<NbuDto> rates = nbuParcer.getRequest();

            Optional<NbuDto> usdRate = rates.stream()
                    .filter(dto->dto.getCc().equalsIgnoreCase(DOLLAR))
                    .findFirst();
            Optional<NbuDto> eurRate = rates.stream()
                    .filter(dto -> dto.getCc().equalsIgnoreCase(EURO) )
                    .findFirst();

            usdRate.ifPresent(dto-> {
                NbuBuyUsd = dto.getRate().setScale(userConfig.getDecimal(), BigDecimal.ROUND_HALF_UP);
               NbuSellUsd = dto.getRate().setScale(userConfig.getDecimal(), BigDecimal.ROUND_HALF_UP);
            });
            eurRate.ifPresent(dto->{
                NbuBuyEuro= dto.getRate().setScale(userConfig.getDecimal(), BigDecimal.ROUND_HALF_UP);
                NbuSellEuro = dto.getRate().setScale(userConfig.getDecimal(), BigDecimal.ROUND_HALF_UP);
            });
        }
        if(userConfig.getBanks().contains(MONO_BANK)){
            MonoParcer monoParcer = new MonoParcer();
            List<MonoDto> rates = monoParcer.getRequest();

            Optional<MonoDto> usdRate = rates.stream()
                    .filter(dto->dto.getCurrencyCodeA()==840)
                    .findFirst();
            Optional<MonoDto> eurRate = rates.stream()
                    .filter(dto -> dto.getCurrencyCodeA()==978)
                    .findFirst();

            usdRate.ifPresent(dto-> {
                MonoBuyUsd = dto.getRateBuy().setScale(userConfig.getDecimal(), BigDecimal.ROUND_HALF_UP);
                MonoSellUsd = dto.getRateSell().setScale(userConfig.getDecimal(), BigDecimal.ROUND_HALF_UP);
            });
            eurRate.ifPresent(dto->{
                MonoBuyEuro= dto.getRateBuy().setScale(userConfig.getDecimal(), BigDecimal.ROUND_HALF_UP);
                MonoSellEuro = dto.getRateSell().setScale(userConfig.getDecimal(), BigDecimal.ROUND_HALF_UP);
            });
        }



    }




    public  String getInfo(UserConfig userConfig){
        if(userConfig.getBanks().isEmpty()){
            userConfig.addBank(PRIVAT_BANK);
        }
        if(userConfig.getCurrentCurrencies().isEmpty()){
            userConfig.addCurrency(DOLLAR);
        }
        getInfoCurrency(userConfig);
        return getFullInfoText(userConfig);
    }

    public  String getFullInfoText(UserConfig userConfig){
        StringBuilder stringBuilder = new StringBuilder();
        for(String bank : userConfig.getBanks()){
            for (String currency : userConfig.getCurrentCurrencies()){
                List<BigDecimal> exactValues = test(bank, currency);
                   stringBuilder.append("Курс у ")
                           .append(bank)
                           .append(" ")
                           .append(currency)
                           .append("/UAH\n")
                           .append("Купівля: ")
                           .append(exactValues.get(0))
                           .append("\n")
                           .append("Продаж: ")
                           .append(exactValues.get(1))
                           .append("\n \n");
            }
        }
        return  stringBuilder.toString();
    }

    public List<BigDecimal> test(String bank, String currency){

       if (bank.equals(PRIVAT_BANK)){
           List<BigDecimal>result;
           if(currency.equals(DOLLAR)){
               result = List.of(getPrivateBuyUsd(), getPrivatSellUsd());
           }else{
               result = List.of(getPrivatBuyEuro(), getPrivatSellEuro());
           }
           return  result;
       }
        if (bank.equals(NBU_BANK)){
            List<BigDecimal>result;
            if(currency.equals(DOLLAR)){
                result = List.of(getNbuBuyUsd(), getNbuSellUsd());
            }else{
                result = List.of(getNbuBuyEuro(), getPrivatSellEuro());
            }
            return  result;
        }
        if (bank.equals(MONO_BANK)){
            List<BigDecimal>result;
            if(currency.equals(DOLLAR)){
                result = List.of(getMonoBuyUsd(), getMonoSellUsd());
            }else{
                result = List.of(getMonoBuyEuro(), getMonoSellEuro());
            }
            return  result;
        }
        return new ArrayList<>();
    }


    public BigDecimal getPrivateBuyUsd(){
        return PrivateBuyUsd;
    }

    public BigDecimal getPrivatSellUsd() {
        return PrivatSellUsd;
    }

    public BigDecimal getPrivatBuyEuro() {
        return PrivatBuyEuro;
    }

    public BigDecimal getPrivatSellEuro() {
        return PrivatSellEuro;
    }

    public BigDecimal getMonoBuyUsd() {
        return MonoBuyUsd;
    }

    public BigDecimal getMonoSellUsd() {
        return MonoSellUsd;
    }

    public BigDecimal getMonoBuyEuro() {
        return MonoBuyEuro;
    }

    public BigDecimal getMonoSellEuro() {
        return MonoSellEuro;
    }

    public BigDecimal getNbuBuyUsd() {
        return NbuBuyUsd;
    }

    public BigDecimal getNbuSellUsd() {
        return NbuSellUsd;
    }

    public BigDecimal getNbuBuyEuro() {
        return NbuBuyEuro;
    }

    public BigDecimal getNbuSellEuro() {
        return NbuSellEuro;
    }
}