package vista;

import control.ConfiguracionControl;
import control.ControlVentana;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo.Unidad;
import hibernateControls.UnidadesControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class UnidadesController implements Initializable {

    @FXML
    private ComboBox<String> CmbBlock;

    @FXML
    private TextField TxtApellido;

    @FXML
    private Button BtnCancelar;

    @FXML
    private TextField TxtNombre;

    @FXML
    private ComboBox<String> CmbPropietarioInquilino;

    @FXML
    private TextField TxtPuerta;

    @FXML
    private Button BtnAceptar;

    @FXML
    private TextField TxtTelefono;

    @FXML
    private ComboBox<String> CmbActivo;

    @FXML
    private ComboBox<Integer> CmbBlockTorre;

    @FXML
    private TextField TxtEmail;

    @FXML
    private DatePicker CmbFecha;
    
    @FXML
    private Label LblInfo;
    
    ControlVentana cv= new ControlVentana();
  
    public void Aceptar(ActionEvent event) {
     
        try{
            Unidad uni; 
            if(validaDatos()){
                uni = new Unidad(ConfiguracionControl.traeUltimoId("Unidad"), 
                            CmbBlock.getValue(),
                            CmbBlockTorre.getValue(),
                            ConfiguracionControl.StringAInt(TxtPuerta.getText()),
                            TxtNombre.getText(),
                            TxtApellido.getText(),
                            TxtTelefono.getText(),
                            TxtEmail.getText(),
                            PropietarioInquilino(CmbPropietarioInquilino.getValue()),
                            ConfiguracionControl.TraeFecha(CmbFecha.getValue()),
                            ActivoInactivo(CmbActivo.getValue()));
                UnidadesControl uc=new UnidadesControl();                       
                uc.guardarUnidad(uni);
                ConfiguracionControl.ActualizaId("Unidad");
                LblInfo.setText("Guardado correctamente");  
                limpiarForm();
                cv.creaVentanaError("Se ha creado correctamente", "information"); 
                cv.creaVentanaNotificacion("Correcto","Se ha cargado Correctamente", 1,"tick");            
            }            
        }
        catch(Exception ex){ 
            String error=ex.getMessage();
            cv.creaVentanaError(error, "error"); 
            cv.creaVentanaNotificacion("Error",error, 3,"error");
            LblInfo.setText("Error al guardar "+ex.getMessage()); 
        }       
    }
    
     public boolean validaDatos(){
        boolean respuesta=true;
        if(CmbBlock.getValue()==null){              
            respuesta= false;
            LblInfo.setText("Falta Block");
            return respuesta;
        }
        else if(CmbBlockTorre.getValue()==null){
            respuesta= false;
            LblInfo.setText("Falta Torre");
             return respuesta;
        }
        else if(TxtPuerta.getText().equals("")){
            respuesta= false;
            LblInfo.setText("Falta Puerta");                    
            return respuesta;
        }
        else if(TxtNombre.getText().equals("")){
            respuesta= false;
            LblInfo.setText("Falta Nombre");
            return respuesta;
        }
        else if(TxtApellido.getText().equals("")){
            respuesta= false;
            LblInfo.setText("Falta Apellido");
            return respuesta;
        }
        else if(TxtTelefono.getText().equals("")){
            respuesta= false;
            LblInfo.setText("Falta Telefono");
            return respuesta;
        }
        else if(CmbPropietarioInquilino.getValue()==null){
            respuesta= false;
            LblInfo.setText("Falta Propietario/Inquilino");
            return respuesta;
        }
        else if(CmbFecha.getValue()==null){
            respuesta= false;
            LblInfo.setText("Falta Fecha");
            return respuesta;
        }
        return respuesta;
    }

    
    public void Cancelar(ActionEvent event) {      
        Stage stage = (Stage) BtnCancelar.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
     LblInfo.setText("");
     cargarComboBlock();
     cargarComboTorre();
     cargarPropietarioInquilino();
     cargarComboActivo();
     CmbActivo.getSelectionModel().select(0);
    }
    
    public void cargarComboBlock(){
       ObservableList<String> options = 
    FXCollections.observableArrayList("A","B","C","D","E");
       CmbBlock.setItems(options);
    }
    
    public void cargarComboTorre(){
        ObservableList<Integer> list;
        list=FXCollections.observableArrayList(1,2,3,4,5,6);
        CmbBlockTorre.setItems(list);
    }
    
    public void cargarPropietarioInquilino(){
        ObservableList<String> list;
        list=FXCollections.observableArrayList("Propietario", "Inquilino");
        CmbPropietarioInquilino.setItems(list);
    }
 
     public void cargarComboActivo(){
        ObservableList<String> list;
        list=FXCollections.observableArrayList("Activo","Inactivo");
        CmbActivo.setItems(list);
    }
     
     public boolean PropietarioInquilino(String str){
         boolean respuesta=false;
         if(str.equals("Propietario")){
             respuesta=true;
         }
         return respuesta;
     } 
     
     public boolean ActivoInactivo(String str){
         boolean respuesta=false;
         if(str.equals("Activo")){
             respuesta=true;
         }
         return respuesta;
     } 
     
     public void limpiarForm(){
         CmbBlock.setValue(null);         
         CmbBlockTorre.setValue(null);
         TxtPuerta.setText("");
         TxtNombre.setText("");
         TxtApellido.setText("");
         TxtTelefono.setText("");
         TxtEmail.setText("");
         CmbPropietarioInquilino.setValue(null);
         CmbFecha.setValue(null);
         CmbActivo.setValue(null);
     }
        
}
