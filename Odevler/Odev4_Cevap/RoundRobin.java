package Odev4_Cevabi_RoundRobin;

/**
 *
 * @author Ahmet
 */
class Eleman{
    int icerik;
    Eleman ileri;
    Eleman(int icerik){
        this.icerik = icerik;
        ileri = null;
    }
}

class Kuyruk{
    Eleman bas , son = null;
    
    Boolean isEmpty(){
        return bas == null;
    }
    
    public void add(int sayi){
        Eleman eleman = new Eleman(sayi);
        if(!isEmpty()){
            son.ileri = eleman;
        }
        else {
            bas = eleman;
        }
        son = eleman;
    }
    
    public Eleman remove(){
        Eleman tmpBas = bas;
        int q = 10;
        if(!isEmpty()){
            if(q>=bas.icerik){
                bas = bas.ileri;
            }
            else{
                int kalanIs = bas.icerik - q;
                add(kalanIs);
                bas = bas.ileri;
            }
            if(bas == null){
                son = null;
            }
        }
        return tmpBas;
    }
    
    public void Listele(){
        Eleman tmp = bas;
        while(tmp!=null){
            System.out.print(tmp.icerik+" ");
            tmp = tmp.ileri;
        }
        System.out.println();
    }
    
}

public class RoundRobin {
    public static void main(String[] args) {
        Kuyruk kuyruk = new Kuyruk();
        kuyruk.add(1);
        kuyruk.add(2);
        kuyruk.add(23);
        kuyruk.add(32);
        kuyruk.add(48);
        kuyruk.add(75);
        kuyruk.add(95);
        kuyruk.add(100);
        kuyruk.Listele();
        System.out.println("==============");
        while(!kuyruk.isEmpty()){
            kuyruk.remove();
            kuyruk.Listele();
        }
    }
}
