package control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class controlXML {
    
     public static String DevuelveValor(String path, String split) throws IOException{
        String file=readFile(path);
        String[] str3 = file.split("<" + split + ">");
        String prueba = str3[1];
        String[] str4 = prueba.split("</" + split + ">");
        prueba = str4[0]; 
        return prueba;
    }
     
     public static String readFile(final String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
	 sCurrentLine = sCurrentLine + br.readLine();
	 sb.append(sCurrentLine);
            }
        } catch (Exception e) {
            e.getMessage();
        }        
        return sb.toString();
    }
     
     public static String DevuelveValorLog() throws IOException{
        String file=readFile("C:\\app.dbj");
        String[] str3 = file.split("<" + "log" + ">");
        String prueba = str3[1];
        String[] str4 = prueba.split("</" + "log" + ">");
        prueba = str4[0]; 
        return prueba;
    }
     
     public static void creaArchivo(String paraArchivo) throws Exception{
         try{
             File file=new File(DevuelveValorLog());
             FileWriter Escribir= new FileWriter(file, true);
             Escribir.write(paraArchivo);
             Escribir.close();             
         }
         catch(Exception ex){
             throw new Exception(ex);
         }
     }
}
