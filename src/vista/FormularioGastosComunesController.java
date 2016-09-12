package vista;

import TablasJavaFx.UnidadFx;
import control.ConfiguracionControl;
import control.ControlUtil;
import control.ControlVentana;
import hibernateControls.GastosComunesControl;
import hibernateControls.MontosControl;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Gastoscomunes;
import modelo.Unidad;
import eu.hansolo.enzo.notification.*;

public class FormularioGastosComunesController implements Initializable {
    
    UnidadFx uni;
    URL ur;
    String periodo="";
    int periodoInt=0;
    MontosControl mc=new MontosControl();
    ConfiguracionControl cc= new ConfiguracionControl();
    GastosComunesControl gcc= new GastosComunesControl();
           
    @FXML
    private ComboBox<String> CmbMoneda;
    
    @FXML
    private Label LblPeriodo;

    @FXML
    private Label LblUnidad; 
    
    @FXML
    private Label LblMoneda;

    @FXML
    private TextField TxtMonto;

    @FXML
    private CheckBox ChkBonificacion;

    @FXML
    private DatePicker CmbFechaPago;

    @FXML
    private Label LblMonto;
    
    @FXML
    Button BtnCancelar;
    
    @FXML
    void Aceptar(ActionEvent event) {
        Gastoscomunes gc= new Gastoscomunes();
        UnidadFx ufx=new UnidadFx();
        Unidad unidad=new Unidad();
        try {
            unidad=ufx.devuelveUnidad(uni);
        } catch (ParseException ex) {
           //***************Agregar Label info
        }
        gc.setUnidad(unidad);
        gc.setPeriodo(periodo);
        gc.setMonto_1(Integer.parseInt(TxtMonto.getText()));
        
        //trae solo monto en pesos verificar esto.
        gc.setMonto(mc.TraeMontoPesos());
        gc.setIdGastosComunes(ConfiguracionControl.traeUltimoId("gastoscomunes"));
        gc.setFechaPago(ConfiguracionControl.TraeFecha(CmbFechaPago.getValue()));
        gc.setEstado(2);
        gc.setBonificacion(true);
        gc.setPeriodoInt(periodoInt);
        try {
            gcc.guardarGastosComunes(gc);
            ConfiguracionControl.ActualizaId("gastoscomunes");
            control.ControlVentana cv= new ControlVentana();            
            String str="se ha cargado correctamente";                       
            cv.creaVentanaError(str, "tick");
            Cancelar(event);
        }         
        catch (Exception ex){            
            control.ControlVentana cv= new ControlVentana();
            String str="Hubo un error al intentar guardar: " + ex.getMessage();            
            cv.creaVentanaError(str, "error");
        }            
    }

    @FXML
    void Cancelar(ActionEvent event) {
        Stage stage = (Stage) BtnCancelar.getScene().getWindow();
        stage.close();        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       if(uni!=null){
           cargarComboMoneda();
           CmbMoneda.setValue("Pesos Uruguayos");
           CmbFechaPago.setValue(LocalDate.now());
           LblUnidad.setAlignment(Pos.CENTER);
           LblUnidad.setText(uni.getNombre() + " " + uni.getApellido() + " - " + uni.getPuerta());
           ControlUtil cu= new ControlUtil();
           periodo=cu.devuelvePeriodoActual();
           periodoInt=cu.devuelvePeriodoActualInt();
           String str= "Periodo: " + periodo;
           LblPeriodo.setText(str);
       }
       ur=url;
    }    
    
   public void initData(UnidadFx uni){
       this.uni=uni;
       initialize(ur, null);
   }
   
   public void cargarComboMoneda(){
        ObservableList<String> list;
        list=FXCollections.observableArrayList("Pesos Uruguayos", "Unidades Indexadas");
        CmbMoneda.setItems(list);
    }
   
   public void cambiaMoneda(ActionEvent event) {
       if(CmbMoneda.getValue().equals("Pesos Uruguayos")){
           LblMoneda.setText("$");
       }
       else{
           LblMoneda.setText("UI");
       }       
    }  
}
