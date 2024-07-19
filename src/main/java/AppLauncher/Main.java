package AppLauncher;

import BankDto.MonoRate;
import Parce.MonoParcer;
import Parce.NBUParcer;
import Parce.PrivatParcer;
import dto.MonoDto;
import dto.PrivatDto;

public class Main {
    public static void main(String[] args) {
        /*MonoParcer mono = new MonoParcer();
        System.out.println(mono.getRequest());
        NBUParcer nbuParcer = new NBUParcer();
        System.out.println(nbuParcer.getRequest())*/;
        PrivatParcer privat = new PrivatParcer();
        System.out.println(privat.getRequest());
    }
}
