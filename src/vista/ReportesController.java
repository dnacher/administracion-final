package vista;

import control.ConfiguracionControl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ReportesController implements Initializable {
    
    @FXML
    private Button BtnCancelar;

    @FXML
    private ComboBox<String> CmbReportes;
    
    @FXML
    private Label LblInfo;
    
    String opcion="";
    ConfiguracionControl cc=new ConfiguracionControl();

    public void Aceptar(ActionEvent event) {
        LblInfo.setText("");
        switch(opcion){
            case "Todas las Unidades":
                cc.generarReporte("ReporteUnidad");
                break;
            case "Graficas":
                cc.generarReporte("newReport");
                break;
            case "Todos los Usuarios":
                cc.generarReporte("reporte");
                break;
            default:
                LblInfo.setText("Debe seleccionar una opcion");
        }
    }
    
    public void Cancelar(ActionEvent event) {
        Stage stage = (Stage) BtnCancelar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarComboReportes();
    } 
    
    public void cargarComboReportes(){
        ObservableList<String> list;
        list=FXCollections.observableArrayList("Todas las Unidades", "Graficas", "Todos los Usuarios");
        CmbReportes.setItems(list);
    }
    
    public void cambiaReportes(ActionEvent event) {
        opcion=CmbReportes.getValue();
    }
    
}
