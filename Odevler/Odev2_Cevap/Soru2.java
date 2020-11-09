package Odev2_Cevabi_Stack_Yigit;

/**
 *
 * @author Ahmet
 */

import java.util.Scanner;

class Eleman{
    int icerik;
    Eleman ileri;
    
    public Eleman(int icerik){
       this.icerik = icerik;
       ileri = null;
    }
}

class Yigit {

    Eleman ustDeger;
    
    public Yigit() {
        ustDeger = null;
    }

    public Boolean isEmpty() {
        return ustDeger == null;
    }

    public void push(int binary) {
        Eleman sayi = new Eleman(binary);
        sayi.ileri = ustDeger;
        ustDeger = sayi;
    }

    public Eleman pop(){
       Eleman temp = ustDeger;
       if(!isEmpty()){
           ustDeger = ustDeger.ileri;
           return temp;
       }
       else
           return temp;
   }

    public int top() {
        if (!isEmpty()) {
            return ustDeger.icerik;
        } else {
            return -1;
        }
    }

    public void listele(){
        Eleman temp = ustDeger;
        if(!isEmpty()){
            while(temp != null){
                System.out.println(temp.icerik);
                temp = temp.ileri;
            }
        }
        else{
            System.out.println("Stack/Yığıt boş");
        }
    }
}
public class Soru2 {

    static Yigit stack = new Yigit();
    static String binaryString = "";
    
    static void decimalToBinary(int decimal){

        int binary;
        
        while(decimal>1){
            binary = decimal % 2;
            decimal = decimal / 2;
            stack.push(binary);
            
            if(decimal == 1){
                stack.push(decimal);
            }
        }
//        stack.listele();
    }
    
    static String binaryNotation(Eleman sayi){
        
        binaryString = binaryString + Integer.toString(sayi.icerik);
        
        return binaryString;
    }

    public static void main(String[] args) {
        
        Scanner klavye = new Scanner(System.in);
        System.out.print("Sayı girişi yapınız: "); 
        int sayi = klavye.nextInt();
        
        decimalToBinary(sayi);
        
        while(!stack.isEmpty()){
            binaryNotation(stack.pop());
        }
        
        System.out.println("Decimal to Binary = > "+binaryString);
    }
}
