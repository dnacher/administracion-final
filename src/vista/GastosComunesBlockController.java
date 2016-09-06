package vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;


public class GastosComunesBlockController implements Initializable {
        
    int torre=0;
    boolean A=false;
    boolean B=false;
    boolean C=false;
    boolean D=false;
    boolean E=false;
    
    
     @FXML
    private Button BtnA;
     
    @FXML
    private Button BtnB;
      
    @FXML
    private Button BtnC;
       
    @FXML
    private Button BtnD;
        
    @FXML
    private Button BtnE;
    
    @FXML
    private Button BtnCancelar;

    @FXML
    private ComboBox<Integer> CmbTorre;

    @FXML
    private Button BtnAceptar;

    @FXML
    private Button BtnCambiarVIsta;

    @FXML
    void BlockA(ActionEvent event) {
        valida("A");        
    }

    @FXML
    void BlockB(ActionEvent event) {        
        valida("B");
    }

    @FXML
    void BlockC(ActionEvent event) {
         valida("C");
    }

    @FXML
    void BlockD(ActionEvent event) {
         valida("D");
    }

    @FXML
    void BlockE(ActionEvent event) {
        BtnE.setDisable(true);
         valida("E");
    }

    @FXML
    public void CambiarVista(ActionEvent event) {

    }

    @FXML
    public void TorreCambia(ActionEvent event) {
        torre=CmbTorre.getValue();
    }

    @FXML
    public void Aceptar(ActionEvent event) {
        System.out.println("Block: "+ getBlock() + " Torre: " + torre);
    }

    @FXML
    public void Cancelar(ActionEvent event) {
        Stage stage = (Stage) BtnCancelar.getScene().getWindow();
        stage.close();
    }
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarComboTorre();
        BtnE.setDisable(false);
    } 
    
   public void cargarComboTorre(){
        ObservableList<Integer> list;
        list=FXCollections.observableArrayList(1,2,3,4,5,6);
        CmbTorre.setItems(list);
    }
   
   public void valida(String block){
       switch(block){
       case "A":
           todosFalse();
           A=true;
           break;
       case "B":
           todosFalse();
           B=true;
           break;
       case "C":
           todosFalse();
           C=true;
           break;
       case "D":
           todosFalse();
           D=true;
           break;
       case "E":
           todosFalse();
           E=true;
           break;
        }   
       todosActivos();
   }  
   
   
   public void todosFalse(){
       A=false;
       B=false;
       C=false;
       D=false;
       E=false;
   }
   
   public void todosActivos(){
       BtnA.setDisable(A);
       BtnB.setDisable(B);
       BtnC.setDisable(C);
       BtnD.setDisable(D);
       BtnE.setDisable(E);       
   }
   
   public String getBlock(){
       String respuesta;
       if(A){
           respuesta="A";
           return respuesta;
       }
       else if(B){
           respuesta="B";
           return respuesta;
       }
       else if(C){
           respuesta="C";
           return respuesta;
       }
       else if(D){
           respuesta="D";
           return respuesta;
       }
       else {
           respuesta="E";
           return respuesta;
       }
   }
    
}
