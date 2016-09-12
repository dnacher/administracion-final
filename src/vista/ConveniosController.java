package vista;

import TablasJavaFx.UnidadFx;
import control.ConfiguracionControl;
import control.ControlVentana;
import hibernateControls.CotizacionesControl;
import hibernateControls.GastosComunesControl;
import hibernateControls.MontosControl;
import hibernateControls.UnidadesControl;
import java.io.IOException;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import modelo.Cotizaciones;
import modelo.Gastoscomunes;
import modelo.Monto;
import modelo.Unidad;


public class ConveniosController implements Initializable {

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
  List<Gastoscomunes> listaGastosComunes=new ArrayList<>();
  
  
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
        
        Table.getColumns().addAll(Nombre, Apellido, Block,Torre,Puerta);
        cargaTabla(retorno);
        cargaGrafica("",0);
    }   
    
    @FXML
    public void aceptar(ActionEvent event) throws IOException {      
        LblInfo.setText("");
        Unidad uni=null;
        ufx = Table.getSelectionModel().getSelectedItem();
        if(ufx!=null){
            CotizacionesControl cco= new CotizacionesControl();
            UnidadFx unifx = Table.getSelectionModel().getSelectedItem();               
            try {         
                uni = unifx.devuelveUnidad(ufx);
            } 
            catch (ParseException ex) {
                System.out.println(ex.getMessage());
            }
            List<Cotizaciones>list=cco.TraePeriodosSinPagarUsuario(uni);
            MontosControl mc= new MontosControl();
            
            int id=ConfiguracionControl.traeUltimoId("gastoscomunes");
            for(Cotizaciones c: list){
                Gastoscomunes gc=new Gastoscomunes();
                gc.setIdGastosComunes(id);
                gc.setBonificacion(false);
                gc.setEstado(1);
                gc.setMonto(mc.TraeMontoPesos());
                gc.setMonto_1(c.getCotizacion());
                gc.setPeriodo(c.getPeriodo());
                gc.setPeriodoInt(c.getPeriodoInt());
                gc.setUnidad(uni);
                listaGastosComunes.add(gc);
                System.out.println(c.toString());
                id++;
            }
            GastosComunesControl gcc= new GastosComunesControl();
                try {
                    ConfiguracionControl.ActualizaIdXId("gastoscomunes", id);
                    gcc.guardarListaGastosComunes(listaGastosComunes);
                } catch (Exception ex) {
                    cv.creaVentanaError(ex.getMessage(), "error");
                }
            System.out.println("----------------------");
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
            data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
                int num=(int)data.getPieValue();
                double porc=(((double)num*(double)100)/total);
                DecimalFormat df = new DecimalFormat("#.#");
                df.setRoundingMode(RoundingMode.CEILING);
                String porcent=df.format(porc);
                LblInfoPieChart.setText(data.getName()+ ": " + num + " (" + porcent + " % aprox.)");
            });
        }
    }
}

