package ImportacionDatos;

import control.ConfiguracionControl;
import hibernateControls.UnidadesControl;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import jxl.Sheet;
import jxl.Workbook;
import modelo.Unidad;

public class ImportarDatosExcel {
    
    UnidadesControl uc= new UnidadesControl();
    int contador=1; 
    Stage primaryStage;
    Pane root;
    Scene scene;
       
    
    public void ImportarUnidadesDeExcel(File file){       
        try{            
            Workbook ArchivoExcel=Workbook.getWorkbook(file);
            for(int hojas=0; hojas< ArchivoExcel.getNumberOfSheets();hojas++){
                Sheet hoja= ArchivoExcel.getSheet(hojas);
                int numColumnas= hoja.getColumns();
                int numFilas=hoja.getRows();
                String dato;
                for(int fila=1; fila<numFilas;fila++){
                    int total=numFilas;                                  
                    Unidad uni= new Unidad();
                    for(int columna=0; columna<numColumnas;columna++){
                        dato=hoja.getCell(columna, fila).getContents();
                        double subtotal=retorno((double)fila+1, (double)total); 
                        cargaUnidad(contador,uni,dato,subtotal);
                    }
                }
            }            
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }    
    
    public double retorno(double numero, double total){        
        double retorno=numero/total;
        return retorno;        
    }
    
    public Unidad cargaUnidad(int elcontador, Unidad uni, String dato, double subtotal) throws Exception{        
        switch(elcontador){                        
                        case 1:                            
                            uni.setIdUnidad(ConfiguracionControl.traeUltimoId("Unidad"));                            
                            contador++;
                            break;
                        case 2:
                            uni.setBlock(dato);
                            contador++;
                            break;
                        case 3:
                            uni.setTorre(Integer.parseInt(dato));
                             contador++;
                            break;  
                        case 4:
                            uni.setPuerta(Integer.parseInt(dato));
                             contador++;
                            break;  
                        case 5:
                            uni.setNombre(dato);
                             contador++;
                            break;  
                        case 6:
                            uni.setApellido(dato);
                            contador++;
                            break;  
                        case 7:
                            uni.setTelefono(dato);
                             contador++;
                            break;  
                        case 8:
                            uni.setMail(dato);
                             contador++;
                            break;  
                        case 9:
                            if(dato.equals("1")){
                                uni.setPropietarioInquilino(true);
                            }
                            else{
                                uni.setPropietarioInquilino(false);
                            }                            
                             contador++;
                            break;  
                        case 10:
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");                            
                            uni.setFechaIngreso(formatter.parse(dato));
                            contador++;
                            break;  
                        case 11:
                            if(dato.equals("1")){
                                uni.setActivo(true);
                            }
                            else{
                                uni.setActivo(false);
                            }       
                            contador=1;
                            try{
                            uc.guardarUnidad(uni);
                            }
                            catch(Exception ex){
                               throw new Exception();
                            }
                            break; 
         }        
        return uni;
    }
   
}
