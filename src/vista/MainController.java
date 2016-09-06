package vista;

import TablasJavaFx.UnidadFx;
import control.ControlVentana;
import hibernateControls.UnidadesControl;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import modelo.Unidad;


public class MainController implements Initializable {

  @FXML
  private TableView<UnidadFx> Table; 
  
  @FXML
  private ComboBox<String> CmbBlock;
  
  @FXML
  private ComboBox<Integer> CmbTorre;
  
  @FXML
  Label LblInfo;
  
  ControlVentana cv= new ControlVentana();
  UnidadesControl uc= new UnidadesControl();
  List<Unidad>list;        
  ObservableList<UnidadFx> retorno;
  UnidadFx ufx= new UnidadFx();
  
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
       cargarComboBlock();
       cargarComboTorre();
       list=uc.TraeUnidades();       
                
       List<UnidadFx> li=ufx.getLista(list);       
       retorno = FXCollections.observableList(li);
       TableColumn firstNameCol = new TableColumn("Nombre");
       TableColumn lastNameCol = new TableColumn("Apellido");
       TableColumn emailCol = new TableColumn("Email");
       TableColumn FechaCol = new TableColumn("Fecha");
        
   firstNameCol.setMinWidth(100);
       firstNameCol.setCellValueFactory(
                new PropertyValueFactory<>("nombre"));
       
       
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<>("apellido"));
 
       
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<>("mail"));
        
        
        FechaCol.setMinWidth(50);
        FechaCol.setCellValueFactory(
                new PropertyValueFactory<>("fechaIngreso"));
        Table.getColumns().addAll(firstNameCol, lastNameCol, emailCol,FechaCol);
        cargaTabla(retorno);
    }   
    
      @FXML
    public void aceptar(ActionEvent event) throws IOException {          
        LblInfo.setText("");
        UnidadFx uni = Table.getSelectionModel().getSelectedItem();
        if(uni!=null){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/FormularioGastosComunes.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.getIcons().add(new Image("/vista/imagenes/convenios.png"));
        stage.setScene(
        new Scene((Pane) loader.load()));
        FormularioGastosComunesController controller = 
        loader.<FormularioGastosComunesController>getController();
        controller.initData(uni);
        stage.show();   
        }
        else{
            LblInfo.setText("Debe seleccionar una Unidad");
        }
    }
   
    public void Mostrar(ActionEvent event) {       
        try{
        LblInfo.setText("");
        List<Unidad> listaTorreBlock=uc.TraeUnidadesXBlockTorre(CmbBlock.getValue(), CmbTorre.getValue());
        List<UnidadFx> li=ufx.getLista(listaTorreBlock);       
        retorno = FXCollections.observableList(li);
        cargaTabla(retorno);
        }
        catch(Exception ex){
            LblInfo.setText("Debe seleccionar valores de Block y Torre para buscar");
        }
    }
    
    public void MostrarTodos(ActionEvent event) {       
        List<Unidad> listaTotal=uc.TraeUnidades();
        List<UnidadFx> li=ufx.getLista(listaTotal);       
        retorno = FXCollections.observableList(li);
        cargaTabla(retorno);
    }
    
    public void cargarComboBlock(){
       ObservableList<String> options = 
    FXCollections.observableArrayList("A","B","C","D","E");
       CmbBlock.setItems(options);
    }
    
    public void cargarComboTorre(){
        ObservableList<Integer> listaTorres;
        listaTorres=FXCollections.observableArrayList(1,2,3,4,5,6);
        CmbTorre.setItems(listaTorres);
    }
    
    public void cargaTabla(ObservableList<UnidadFx> retorno){
        LblInfo.setText("se muestran " + retorno.size() + " registros");
        Table.setItems(retorno);     
    }
}
