package vista;

import control.ConfiguracionControl;
import control.controlXML;
import hibernateControls.UnidadesControl;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jxl.Sheet;
import jxl.Workbook;
import modelo.Unidad;

public class CargarMasivasController implements Initializable {
    
    int contador=1; 
    UnidadesControl uc= new UnidadesControl();    
    List<Unidad> list= new ArrayList<>();
    int idUnidad;
    
    @FXML
    private Button BtnCancelar;

    @FXML
    private ComboBox<String> CmbMasivas;
    
    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private ProgressBar progressBar;    
    
    @FXML
    private Label LblInfo;

    @FXML
    void Aceptar(ActionEvent event) throws IOException {
        try{
        switch(CmbMasivas.getValue()){
            case "Unidades":                
                final FileChooser fileChooser = new FileChooser();
                progressBar.setProgress(-1);
                progressIndicator.setProgress(-1);
                LblInfo.setText("trabajando, espere por favor");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Libro Excel 97-2003", "*.xls"));
                Stage stage = (Stage) BtnCancelar.getScene().getWindow();
                File file = fileChooser.showOpenDialog(stage); 
                cargaUnidades(file);
                break;
            default:
               
                break;
        }
        }
        catch(Exception ex){
            LblInfo.setText("Debe elegir una opcion");      
        }
    }  
    
    public void cargaUnidades(File file){
        try{            
                Workbook ArchivoExcel=Workbook.getWorkbook(file);
                idUnidad=ConfiguracionControl.traeUltimoId("Unidad");
                for(int hojas=0; hojas< ArchivoExcel.getNumberOfSheets();hojas++){                    
                    Sheet hoja= ArchivoExcel.getSheet(hojas);
                    int numColumnas= hoja.getColumns();
                    int numFilas=hoja.getRows();               
                    String dato;
                    for(int fila=1; fila<numFilas;fila++){                                                          
                        Unidad uni= new Unidad();  
                        double num=(double)fila+1/(double)numFilas;
                        progressBar.setProgress(num);
                        progressIndicator.setProgress(num);
                    for(int columna=0; columna<numColumnas;columna++){
                        dato=hoja.getCell(columna, fila).getContents();                       
                        switch(contador){                        
                        case 1:                                    
                            uni.setIdUnidad(idUnidad);
                            idUnidad++;                            
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
                            list.add(uni);
                            LblInfo.setText("ingresado " + uni.getIdUnidad());     
                            }
                            catch(Exception ex){
                               LblInfo.setText(ex.getMessage());
                            }
                            break; 
                        }                           
                    }
                }
            }            
            uc.guardarUnidades(list, idUnidad);            
            LblInfo.setText("Termino el proceso. Por favor verifique el log en " + controlXML.DevuelveValorLog() + " para mas informacion del proceso");
            list.clear();
        }
        catch(Exception ex){
            LblInfo.setText(ex.getMessage());
        }
    }

    @FXML
    void Cancelar(ActionEvent event) {
        Stage stage = (Stage) BtnCancelar.getScene().getWindow();
        stage.close(); 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {      
      cargarComboMasivas();     
    }    
    
    public void cargarComboMasivas(){
        ObservableList<String> listaTorres;
        listaTorres=FXCollections.observableArrayList("Unidades");
        CmbMasivas.setItems(listaTorres);
    }      
}
