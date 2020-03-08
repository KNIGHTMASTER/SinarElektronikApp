package Sinarelektronikapp.util;

/**
 *
 * @author Fauzi
 */
public class FormatRupiah {
    public String FormatRupiah(String angka){
        angka = nilaiBerkebalikan(angka);
        angka = sisipkanTitik(angka);
        angka = nilaiBerkebalikan(angka);
        return "Rp"+angka+",00";
    }
    
    public String nilaiBerkebalikan(String value){
        String [] str=value.split("");
        value = "";
        for(int a=str.length-1; a>0; a--){
            value = value.concat(str[a]);
        }
        return value;
    }
    
    public String sisipkanTitik(String value){
        String [] str=value.split("");
        value = "";
        for(int a=1; a<str.length; a++){
            value = value.concat(str[a]);
            if(a%3 == 0){
                value = value.concat(".");
            }
        }
        return value;
    }
}
