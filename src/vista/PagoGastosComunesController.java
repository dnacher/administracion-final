package vista;

import control.ControlVentana;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class PagoGastosComunesController implements Initializable {
    
    @FXML
    private Button BtnCancelar;

    @FXML
    private RadioButton RMesActual;

    @FXML
    private ToggleGroup radios;

    @FXML
    private Button BtnAceptar;

    @FXML
    private RadioButton ROtroMes;
   
    boolean mesAcutal=true;
    control.ControlVentana cv;
   
    public void otroMes(ActionEvent event) {
        mesAcutal=false;
        
    }

   
    public void mesActual(ActionEvent event) {
        mesAcutal=true;        
    }
    
   
    public void Aceptar(ActionEvent event) throws IOException {
         if(mesAcutal){
             try{
             cv= new ControlVentana();
            cv.crearVentanaMain("Main", "Gastos Comunes");
            Stage stage = (Stage) BtnCancelar.getScene().getWindow();
            stage.close();
             }
             catch(Exception ex){
                 System.out.println(ex.getMessage());
             }
        }
        else{
            System.out.println("Otro Mes");
        }
    }
   
    public void Cancelar(ActionEvent event) {
        Stage stage = (Stage) BtnCancelar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

      
    }    
    
}
