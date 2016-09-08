package control;

import java.util.Calendar;

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
}
