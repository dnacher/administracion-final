package vista;

import control.ControlVentana;
import hibernateControls.CotizacionesControl;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Cotizaciones;

public class CotizacionesController implements Initializable {
    
    List<Cotizaciones> lista= new ArrayList<>();    
    ControlVentana cv= new ControlVentana();
    
    @FXML
    private Button BtnCancelar;

    @FXML
    private ComboBox<String> CmbAnioInicial;

    @FXML
    private ComboBox<String> CmbMesInicial;

    @FXML
    private ComboBox<String> CmbMesFinal;

    @FXML
    private ComboBox<String> CmbAnioFinal;
    
    @FXML
    private TextField TxtMonto;

    @FXML
    void Generar(ActionEvent event) {
        try{
            int monto=Integer.parseInt(TxtMonto.getText());
            int anioInicial=Integer.parseInt(CmbAnioInicial.getValue());
            int anioFinal=Integer.parseInt(CmbAnioFinal.getValue());
            int mesInicial=Integer.parseInt(CmbMesInicial.getValue());
            int mesFinal=Integer.parseInt(CmbMesFinal.getValue());
            for(int i=anioInicial;i<=anioFinal;i++){
                for(int j=mesInicial;j<=mesFinal;j++){
                     Cotizaciones cotizaciones= new Cotizaciones();
                     cotizaciones.setCotizacion(monto);
                    if(j<10){
                        cotizaciones.setPeriodo(i + "-" + "0" + j);
                    }
                     else{
                        cotizaciones.setPeriodo(i + "-" + j);
                    } 
                     
                     lista.add(cotizaciones);
                }
            }
            CotizacionesControl cc= new CotizacionesControl();
            cc.guardarCotizaciones(lista);
            cv.creaVentanaError("Se han cargado correctamente", "tick");
        }
        catch(Exception ex){
            cv.creaVentanaError("Hubo un error al guardar " + ex.getMessage(), "error");
        }
    }

    @FXML
    void Cancelar(ActionEvent event) {
        Stage stage = (Stage) BtnCancelar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargaComboAnio();
        cargaComboMes();
        CmbAnioInicial.getSelectionModel().select("2016");
        CmbAnioFinal.getSelectionModel().select("2016");
        CmbMesFinal.getSelectionModel().select("09");
        CmbMesInicial.getSelectionModel().select("01");
        
    }    
    
    public void cargaComboAnio(){
        ObservableList<String> list;
        list=FXCollections.observableArrayList("1980");
        for(int i=1981; i<2031;i++){            
            list.add(Integer.toString(i));
        }        
        CmbAnioFinal.setItems(list);
        CmbAnioInicial.setItems(list);
    }
    
    public void cargaComboMes(){
        ObservableList<String> list;
        list=FXCollections.observableArrayList("01");
        for(int i=2; i<13;i++){            
            if(i<10){
                list.add("0" + Integer.toString(i));
            }
            else{
                list.add(Integer.toString(i));
            }            
        }        
        CmbMesFinal.setItems(list);
        CmbMesInicial.setItems(list);
    }
    
}
