package Odev3_Cevabi_Stack_Yigit;
/**
 *
 * @author Ahmet Ekmel Yeşilyurt
 */
import java.util.Scanner;

class Eleman{
    char icerik;
    double sayi;
    Eleman(char icerik){
        this.icerik = icerik;
    }
    Eleman(double sayi){
        this.sayi = sayi;
    }
}

class stack{
    
    Eleman dizi[];
    int ustDeger;
    int boyut;
    
    public stack(int boyut){
        dizi = new Eleman[boyut];
        this.boyut = boyut;
        ustDeger = -1;
    }
    
    Boolean isEmpty(){
        return ustDeger == -1;
    }
    
    Boolean isFull(){
        return ustDeger == boyut-1;
    }
    
    Eleman top(){
        return dizi[ustDeger];
    }
    
    void push(char operator){
        Eleman islem = new Eleman(operator); 
        if(!isFull()){
            ustDeger++;
            dizi[ustDeger] = islem;
        }
    }
    
    void push(double sayi){
        Eleman eleman = new Eleman(sayi);
        if(!isFull()){
            ustDeger++;
            dizi[ustDeger] = eleman;
        }
    }
    
    Eleman pop(){
        Eleman temp = dizi[ustDeger];
        if(!isEmpty()){
            ustDeger--;
            return temp;
        }
        return null;
    }
    
    void listele(Boolean kontrol){
        int i = ustDeger;
        if(!isEmpty()){
            while(i != -1){
                if(kontrol == true){
                    System.out.println(dizi[i].icerik); // char tipinde gösterim
                }
                else if(kontrol == false){
                    System.out.println(dizi[i].sayi); // int türünde gösterim
                }
                i--;
            }
        }
        else{
            System.out.println("Stack/Yığıt Boş!");
        }
    }
}

public class Soru1 {
      
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Infix string girişi yapınız: ");
        String infix = sc.next();
        String postfix = infixToPostfix(infix);
        double postfixSonucu = postfixSonucunuBul(postfix);
        String prefix = infixToPrefix(infix);

        System.out.println("Postfix: "+postfix);
        System.out.println("Prefix: "+prefix);
        System.out.println("Postfix sonucu: "+postfixSonucu);
    }
    
    static String infixToPostfix(String infix){
        
        stack yigit = new stack(infix.length());
        
        String postfix = "";
        char popDeger;
        char stackUstDeger; int stackOncelik, infixOncelik;
        
        for(char karakter : infix.toCharArray()){
            switch(karakter){
                case '(':
                case '^':
                case '*':
                case '/':
                case '+':
                case '-':
                    while(!yigit.isEmpty()){
                        stackUstDeger = yigit.top().icerik;
                        stackOncelik = oncelikBelirleme(stackUstDeger);
                        infixOncelik = oncelikBelirleme(karakter);
                        if((stackOncelik>infixOncelik || stackOncelik == infixOncelik)){
                            if(stackOncelik==4){
                                break;
                            }
                            else{
                                popDeger = yigit.pop().icerik;
                                postfix += popDeger;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    yigit.push(karakter);
                    break;
                case ')':
                    while(yigit.top().icerik != '('){
                        char popDeger2 = yigit.pop().icerik;
                        postfix += popDeger2;
                    }
                    yigit.pop(); // en son ( işaretini de stackden çıkar.
                    break;
                default:
                    postfix += karakter;
                    break;
            }    
        }

        // En son stack de kalan karakterler çıkartıldı.
        while(!yigit.isEmpty()){
            postfix += yigit.pop().icerik;
        } 

        return postfix;
    }
    
    static double postfixSonucunuBul(String postfix){
        
        stack yigit = new stack(postfix.length());

        double ust, ustArti_1, sonuc = 0;
        
        for(char karakter: postfix.toCharArray()){
            switch(karakter){
                case '*':
                    if(!yigit.isEmpty()){
                        ust = yigit.pop().sayi;
                        ustArti_1 = yigit.pop().sayi;
                        double carpim = ust * ustArti_1;
                        yigit.push(carpim);
                    }
                    break;
                case '/':
                    if(!yigit.isEmpty()){
                        ust = yigit.pop().sayi;
                        ustArti_1 = yigit.pop().sayi;
                        double bolum = ustArti_1 / ust;
                        yigit.push(bolum);
                    }
                    break;
                case '+':
                    if(!yigit.isEmpty()){
                        ust = yigit.pop().sayi;
                        ustArti_1 = yigit.pop().sayi;
                        double toplam = ust + ustArti_1;
                        yigit.push(toplam);
                    }
                    break;
                case '-':
                    if(!yigit.isEmpty()){
                        ust = yigit.pop().sayi;
                        ustArti_1 = yigit.pop().sayi;
                        double cikarma = ustArti_1 - ust;
                        yigit.push(cikarma);
                    }
                    break;
                default:
                    int krktr = Character.getNumericValue(karakter); 
                    yigit.push(krktr);
            }
        }
            if(!yigit.isEmpty()){
                sonuc = yigit.pop().sayi;
            }
        return sonuc;
    }
    
    static String infixToPrefix(String infix){
        
        stack yigit = new stack(infix.length());
        
        StringBuilder tersCevir = new StringBuilder();
        tersCevir.append(infix);
        tersCevir = tersCevir.reverse();
        String tersInfix = tersCevir.toString();
        
        String prefix = "";
        char popDeger;
        char stackUstDeger; 
        int stackOncelik, infixOncelik=-1;
        
        for(char karakter : tersInfix.toCharArray()){
            switch(karakter){
                case ')':
                case '^':
                case '*':
                case '/':
                case '+':
                case '-':
                    while(!yigit.isEmpty()){
                        stackUstDeger = yigit.top().icerik;
                        stackOncelik = oncelikBelirleme(stackUstDeger);
                        infixOncelik = oncelikBelirleme(karakter);
                        if((stackOncelik>infixOncelik && stackOncelik != infixOncelik)){
                            if(infixOncelik==0){
                                break;
                            }
                            else{
                                popDeger = yigit.pop().icerik;
                                prefix += popDeger;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    yigit.push(karakter);
                    
                    break;
                case '(':
                    while(yigit.top().icerik != ')'){
                        char popDeger2 = yigit.pop().icerik;
                        prefix += popDeger2;
                    }
                    yigit.pop(); // en son ) işaretini de stackden çıkar.
                    break;
                default:
                    prefix += karakter;
                    break;
            }
        }
        
        // En son stack de kalan karakterler çıkartıldı.
        while(!yigit.isEmpty()){
            prefix += yigit.pop().icerik;
        }
        
        StringBuilder tersCevir2 = new StringBuilder();
        tersCevir2.append(prefix);
        tersCevir2 = tersCevir2.reverse();
        prefix = tersCevir2.toString();
        
        return prefix;
    }
    
    static int oncelikBelirleme(char karakter){
        switch(karakter){
            case '(':
                return 4;
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
            case ')':
                return 0;
        }
        return -1;
    }
}
