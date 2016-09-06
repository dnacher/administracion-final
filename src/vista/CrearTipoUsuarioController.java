package vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CrearTipoUsuarioController implements Initializable {

     @FXML
    private CheckBox ChkVerOtrosGastos;

    @FXML
    private CheckBox ChkModificarReportes;

    @FXML
    private Button BtnCancelar;

    @FXML
    private CheckBox ChkModificarOtrosGastos;

    @FXML
    private CheckBox ChkModificarUnidades;

    @FXML
    private CheckBox ChkModificarGastosComunes;
    
    @FXML
    private Button BtnAceptar;

    @FXML
    private CheckBox ChkVerExportacion;

    @FXML
    private CheckBox ChkVerReportes;

    @FXML
    private CheckBox ChkVerImportacion;

    @FXML
    private CheckBox ChkVerConvenios;

    @FXML
    private CheckBox ChkModificarConvenios;

    @FXML
    private TextField TxtTipoUsuario;
    
    @FXML
    private CheckBox ChkModificarExportacion;

    @FXML
    private CheckBox ChkVerUnidades;

    @FXML
    private CheckBox ChkModificarImportacion;

    @FXML
    private CheckBox ChkVerGastosComunes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    @FXML
    void Aceptar(ActionEvent event) {

    }

    @FXML
    void Cancelar(ActionEvent event) {
        Stage stage = (Stage) BtnCancelar.getScene().getWindow();
        stage.close(); 
    }
}
