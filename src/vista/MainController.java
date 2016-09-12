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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
  private Button BtnCancelar;
  
  @FXML
  Label LblInfo;
  
  @FXML
  PieChart pieChart;
  
  @FXML
  Label LblInfoPieChart;
  
  ControlVentana cv= new ControlVentana();
  UnidadesControl uc= new UnidadesControl();
  List<Unidad>list;        
  ObservableList<UnidadFx> retorno;
  UnidadFx ufx= new UnidadFx();
  
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
       cargarComboBlock();
       cargarComboTorre();
       list=uc.TraeUnidadesGastosComunesNoPago();               
       List<UnidadFx> li=ufx.getLista(list);       
       retorno = FXCollections.observableList(li);
       TableColumn Nombre = new TableColumn("Nombre");
       TableColumn Apellido = new TableColumn("Apellido");
       TableColumn Block = new TableColumn("Block");
       TableColumn Torre = new TableColumn("Torre");
       TableColumn Puerta= new TableColumn("Puerta");
        
   Nombre.setMinWidth(100);
   Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
       
   Apellido.setMinWidth(100);
   Apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
 
   Block.setMinWidth(50);
   Block.setCellValueFactory(new PropertyValueFactory<>("Block"));
   
   Torre.setMinWidth(50);
   Torre.setCellValueFactory(new PropertyValueFactory<>("Torre"));
   
   Puerta.setMinWidth(60);
   Puerta.setCellValueFactory(new PropertyValueFactory<>("Puerta"));
       
       /* emailCol.setMinWidth(180);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<>("mail"));
        
        
        FechaCol.setMinWidth(50);
        FechaCol.setCellValueFactory(
                new PropertyValueFactory<>("fechaIngreso"));*/
        
        Table.getColumns().addAll(Nombre, Apellido, Block,Torre,Puerta);
        cargaTabla(retorno);
        cargaGrafica("",0);
    }   
    
      @FXML
    public void aceptar(ActionEvent event) throws IOException {
     
        //devuelve lista de cotizaciones del usuario, funciona bien
        /*  CotizacionesControl cco= new CotizacionesControl();
        UnidadFx unifx = Table.getSelectionModel().getSelectedItem();
        Unidad uni=null;        
      try {
          uni = unifx.devuelveUnidad(unifx);
      } catch (ParseException ex) {
          System.out.println(ex.getMessage());
      }
        List<Cotizaciones>list=cco.TraePeriodosSinPagarUsuario(uni);
        for(Cotizaciones c: list){
            System.out.println(c.toString());
        }*/
        
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
        stage.showAndWait();
        MostrarTodos(event);
        cargaGrafica("",0);
        }
        else{
            LblInfo.setText("Debe seleccionar una Unidad");
        }
    }
    
    public void Cancelar(ActionEvent event){
        Stage stage = (Stage) BtnCancelar.getScene().getWindow();
        stage.close(); 
    }
   
    public void Mostrar(ActionEvent event) {       
        try{
        LblInfo.setText("");
        List<Unidad> listaTorreBlock=uc.TraeUnidadesXBlockTorreNoPago(CmbBlock.getValue(), CmbTorre.getValue());
        List<UnidadFx> li=ufx.getLista(listaTorreBlock);       
        retorno = FXCollections.observableList(li);
        cargaTabla(retorno);
        cargaGrafica(CmbBlock.getValue(), CmbTorre.getValue());
        }
        catch(Exception ex){
            LblInfo.setText("Debe seleccionar valores de Block y Torre para buscar");
        }
    }
    
    public void MostrarTodos(ActionEvent event) {       
        List<Unidad> listaTotal=uc.TraeUnidadesGastosComunesNoPago();
        List<UnidadFx> li=ufx.getLista(listaTotal);       
        retorno = FXCollections.observableList(li);
        cargaTabla(retorno);
        cargaGrafica("",0);
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
    
    public void cargaGrafica(String block, int torre){
        LblInfoPieChart.setText("");
        int total=uc.totalUnidades(block,torre);
        int totalPago=total-retorno.size();
        int totalNoPago=total-totalPago;
        ObservableList<Data> lista=FXCollections.observableArrayList(                
                new PieChart.Data("No pagó", totalNoPago),
                new PieChart.Data("Pagó", totalPago)
        );
        pieChart.setData(lista);
        
        for(final PieChart.Data data: pieChart.getData()){
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    int num=(int)data.getPieValue();
                    LblInfoPieChart.setText("Total: " + num);
                }
            });
        }
    }
}

