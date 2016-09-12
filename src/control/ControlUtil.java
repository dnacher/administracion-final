package control;

import java.util.Calendar;
import java.util.Date;

public class ControlUtil {
    
    String periodo;
    
    public String devuelvePeriodoActual(){
        
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month=Calendar.getInstance().get(Calendar.MONTH)+1;
        periodo=Integer.toString(year) + "-";
        Agrega0SiNecesita(month);       
        return periodo;
    }
    
    public int devuelvePeriodoActualInt(){
        int periodoInt;
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month=Calendar.getInstance().get(Calendar.MONTH)+1;
        periodo=Integer.toString(year);
        periodo= periodo + Integer.toString(month);
        periodoInt=Integer.parseInt(periodo);
        return periodoInt;
    }
    
    public void Agrega0SiNecesita(int month){        
        if(month<10){
               periodo=periodo + "0" + Integer.toString(month);
           }
        else{
               periodo=periodo + Integer.toString(month);
           }  
   }
    
    public Calendar DateToCalendar(Date date){ 
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }
    
    public int concatenaInt(int num1, int num2){
        String str=String.valueOf(num1) + devuelveMes(num2);
        int retorno=Integer.parseInt(str);
        return retorno;    
    }
    
    public String devuelveMes(int mes){
        String str;
        if(mes<10){
            str="0" + String.valueOf(mes);
        }
        else{
            str=String.valueOf(mes);
        }       
        return str;
    }  
}
