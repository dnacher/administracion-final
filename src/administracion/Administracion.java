package administracion;

import control.ConfiguracionControl;
import control.ControlVentana;
import eu.hansolo.enzo.common.SymbolType;
import eu.hansolo.enzo.fonts.Fonts;
import eu.hansolo.enzo.radialmenu.RadialMenu;
import eu.hansolo.enzo.radialmenu.RadialMenuBuilder;
import eu.hansolo.enzo.radialmenu.RadialMenuItem;
import eu.hansolo.enzo.radialmenu.RadialMenuItemBuilder;
import eu.hansolo.enzo.radialmenu.RadialMenuOptionsBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import static javafx.application.Application.launch;
import javafx.geometry.Pos;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import modelo.Usuario;
import control.LoginControl;

public class Administracion extends Application{
   
    private static final Dimension2D SIZE=new Dimension2D(500, 700);   
    private AnchorPane contentPane;        
    // ContentPane related
    private Label header;
    private Button settingsButton;
    private Button exitButton;
    private Button LoginButton;
    private TextField TxtUsuario;
    private PasswordField TxtPassword;
    private Label LblInfo;    
    ControlVentana cv= new ControlVentana();
    // RadialMenu
    private StackPane radialMenuPane;
    private RadialMenu radialMenu;
    int i=0;
    ConfiguracionControl cc=new ConfiguracionControl();
    Stage stage;
    
    //Crea los items para el menu
        RadialMenuItem Unidades= new RadialMenuItem();     
        RadialMenuItem GastosComunes= new RadialMenuItem();
        RadialMenuItem Convenios= new RadialMenuItem();
        RadialMenuItem OtrosGastos= new RadialMenuItem();
        RadialMenuItem Reportes= new RadialMenuItem();
        RadialMenuItem ConfiguracionUsuarios= new RadialMenuItem();
        RadialMenuItem ImportacionExportacion= new RadialMenuItem();
        RadialMenuItem ProcesosMasivos= new RadialMenuItem();
   
    @Override public void init() {       
        initRadialMenuPane();        
        initContentPane();       
    }   
    
    public void initItems(){
        Unidades=RadialMenuItemBuilder.create().symbol(SymbolType.LOCATION).tooltip("Unidades").size(48).build();       
        GastosComunes=RadialMenuItemBuilder.create().symbol(SymbolType.CALCULATOR).tooltip("Gastos Comunes").size(48).build();       
        Convenios=RadialMenuItemBuilder.create().symbol(SymbolType.CALENDAR).tooltip("Convenios").size(48).build();       
        OtrosGastos=RadialMenuItemBuilder.create().symbol(SymbolType.TOOL).tooltip("Otros Gastos").size(48).build(); 
        Reportes=RadialMenuItemBuilder.create().symbol(SymbolType.CHART).tooltip("Reportes").size(48).build();       
        ConfiguracionUsuarios=RadialMenuItemBuilder.create().symbol(SymbolType.SETTINGS).tooltip("Configuracion Usuarios").size(48).build();       
        ImportacionExportacion=RadialMenuItemBuilder.create().symbol(SymbolType.LAYERS).tooltip("Importacion/Exportacion").size(48).build();               
        ProcesosMasivos=RadialMenuItemBuilder.create().symbol(SymbolType.BRUSH).tooltip("Procesos Masivos").size(48).build();               
    }
    
     public List<RadialMenuItem> DevuelveItems(){        
        List<RadialMenuItem> list= new ArrayList();
        list.add(GastosComunes);
        list.add(Convenios);
        list.add(OtrosGastos);
        list.add(Unidades);
        list.add(ConfiguracionUsuarios);
        list.add(ImportacionExportacion);
        list.add(ProcesosMasivos);
        list.add(Reportes);    
        return list;
    }
    
    public void creaMensajeItemsMenuEntra(RadialMenuItem rmi, String mensajeLabel){
            rmi.setOnMouseEntered(mouseEvent -> {LblInfo.setText(mensajeLabel);});
    }
  
    public void creaMensajeItemsMenuSale(RadialMenuItem rmi){
            rmi.setOnMouseExited(mouseEvent -> {LblInfo.setText("");});
    }
    
    private void initRadialMenuPane() {   
        initItems();
        creaFuncionesMouse();     
        creaMensajes();
        creaMenu();
    }  
    
    public void creaMensajes(){    
        //Lbl info, cuando se para arriba muestra en el Label
        creaMensajeItemsMenuEntra(Unidades, "Unidades");
        creaMensajeItemsMenuSale(Unidades);
        
        creaMensajeItemsMenuEntra(GastosComunes,"Gastos Comunes" );
        creaMensajeItemsMenuSale(GastosComunes);
        
        creaMensajeItemsMenuEntra(GastosComunes,"Gastos Comunes" );
        creaMensajeItemsMenuSale(GastosComunes);
        
        creaMensajeItemsMenuEntra(Convenios,"Convenios" );
        creaMensajeItemsMenuSale(Convenios);
        
        creaMensajeItemsMenuEntra(OtrosGastos,"Otros Gastos" );
        creaMensajeItemsMenuSale(OtrosGastos);
        
        creaMensajeItemsMenuEntra(Reportes,"Reportes" );
        creaMensajeItemsMenuSale(Reportes);
        
        creaMensajeItemsMenuEntra(ImportacionExportacion,"Importacion/Exportacion" );
        creaMensajeItemsMenuSale(ImportacionExportacion);
        
        creaMensajeItemsMenuEntra(ConfiguracionUsuarios,"Configuracion Usuarios" );
        creaMensajeItemsMenuSale(ConfiguracionUsuarios);
        
        creaMensajeItemsMenuEntra(ProcesosMasivos,"Procesos Masivos" );
        creaMensajeItemsMenuSale(ProcesosMasivos);
    }
    
    public void creaMenu(){
        //Crea el menu
        radialMenu = RadialMenuBuilder.create()
                                      .options(RadialMenuOptionsBuilder.create()
                                                                       .degrees(360)
                                                                       .offset(-90)
                                                                       .radius(200)
                                                                       .buttonSize(72)
                                                                       .buttonHideOnSelect(false)
                                                                       .buttonHideOnClose(false)
                                                                       .buttonAlpha(1.0)
                                                                       .buttonVisible(true)
                                                                       .build())
                                      .items(DevuelveItems()).build();
        radialMenu.setPrefSize(250, 250);        
        radialMenuPane = new StackPane(radialMenu);        
        AnchorPane.setLeftAnchor(radialMenuPane, 120d);
        AnchorPane.setTopAnchor(radialMenuPane, 250d);         
        unManageNode(radialMenu); 
        
    }
    
    public void creaFuncionesMouse(){
        //Crea las funciones cuando se hace clic
        Convenios.setOnMousePressed(mouseEvent -> {});        
        Unidades.setOnMousePressed(mouseEvent -> {try {
            cv.crearVentanaMain("Unidades", "Crear Unidades");
            } catch (IOException ex) {
               LblInfo.setText(ex.getMessage());
            }
        });   
        
        GastosComunes.setOnMousePressed(mouseEvent -> {try {
            cv.crearVentanasinCSS("PagoGastosComunes", "Pago Gastos Comunes");
            } catch (IOException ex) {
                Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        ConfiguracionUsuarios.setOnMousePressed(mouseEvent -> {try {
            cv.crearVentanasinCSS("Main", "Pago Gastos Comunes");
            } catch (IOException ex) {
                Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        Reportes.setOnMousePressed(mouseEvent -> {try {
            cv.crearVentanasinCSS("Reportes", "Generar Reportes");
            } catch (IOException ex) {
                Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
            }});
        
        ProcesosMasivos.setOnMousePressed(mouseEvent -> {try {
            cv.crearVentanasinCSS("CargarMasivas", "Cargar Masivas");
            } catch (IOException ex) {
                Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
            }});
        
        OtrosGastos.setOnMousePressed(mouseEvent -> {});
        
        ImportacionExportacion.setOnMousePressed(mouseEvent -> {});
    }
    
    private void Login(){  
        LoginControl lc= new LoginControl();
            Usuario usu=lc.Login(TxtUsuario.getText(), TxtPassword.getText());
            if(usu!=null){                    
                loginCorrecto();
            }
            else{
                LblInfo.setText("Login Incorrecto");
            }
    }
    
    public void loginCorrecto(){
        LblInfo.setText("Login correcto");
        unManageNode(TxtUsuario);
        unManageNode(TxtPassword);
        unManageNode(LoginButton);
        manageNode(radialMenu);
        settingsButton.setDisable(false);
    }
    
    public void ConfigureButton() throws IOException{        
        /*   ********Prueba lanzar todos los Gastos comunes. funciona correctamente
        UnidadesControl uc= new UnidadesControl();
        List<Unidad>list=uc.TraeUnidades();
        GastosComunesControl gcc= new GastosComunesControl();
        gcc.CargasGastosComunesXListaUnidades(list);*/
    }
   
    private void initContentPane() {
        crearTituloVentana();
        crearSettingButton();
        crearExitButton();
        crearLabelInfo();
               
        initLogin();     
        
        definirContentPane();
    }
    
    public void crearTituloVentana(){
        header = new Label("Main Menu");        
        header.getStyleClass().add("header");
        header.setMouseTransparent(true);        
        AnchorPane.setLeftAnchor(header, 0d);
        AnchorPane.setTopAnchor(header, 10d);
    }
    
    public void crearSettingButton(){
        settingsButton = new Button();
        settingsButton.getStyleClass().add("settings-button");        
        settingsButton.setOnAction(event -> {
            try {
                ConfigureButton();
            } catch (IOException ex) {
                Logger.getLogger(Administracion.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        AnchorPane.setRightAnchor(settingsButton, 10d);
        AnchorPane.setTopAnchor(settingsButton, 10d);
        settingsButton.setDisable(true);
    }
    
    public void crearExitButton(){
        exitButton = new Button();
        exitButton.getStyleClass().add("exit-button");
        exitButton.setOnAction(event -> Platform.exit());
        AnchorPane.setRightAnchor(exitButton, 10d);
        AnchorPane.setBottomAnchor(exitButton, 10d);
    }
    
    public void crearLabelInfo(){
        LblInfo= new Label();           
        AnchorPane.setBottomAnchor(LblInfo, 10d);
        AnchorPane.setLeftAnchor(LblInfo, 10d);
        LblInfo.getStyleClass().add("header2");
    }
    
    public void definirContentPane(){
        contentPane = new AnchorPane();
        contentPane.setPrefSize(SIZE.getWidth(), SIZE.getHeight());
        contentPane.getStyleClass().add("content-background");
        contentPane.getChildren().addAll(header, settingsButton, radialMenuPane,exitButton,LoginButton,TxtUsuario,TxtPassword,LblInfo);        
    }
    
    private void initLogin(){    
        //boton login
        LoginButton= new Button();
        LoginButton.setText("Login");          
        LoginButton.setOnAction(event -> Login());
        LoginButton.getStyleClass().add("login-button");
        AnchorPane.setLeftAnchor(LoginButton,200d);
        AnchorPane.setBottomAnchor(LoginButton,200d);
        
        //Texto Usuario
        TxtUsuario=new TextField();        
        TxtUsuario.setText("Usuario");
        TxtUsuario.setAlignment(Pos.CENTER);
        AnchorPane.setLeftAnchor(TxtUsuario,170d);
        AnchorPane.setBottomAnchor(TxtUsuario,400d);
        
        //Texto Password
        TxtPassword=new PasswordField();
        TxtPassword.setAlignment(Pos.CENTER);
        TxtPassword.setPromptText("Contraseña");        
        AnchorPane.setLeftAnchor(TxtPassword,170d);
        AnchorPane.setBottomAnchor(TxtPassword,300d);      
    }
    
    private void initPrefPane() {       
        Label headLabel;
        headLabel = new Label("Preferences");
        headLabel.setFont(Fonts.robotoRegular(24));
        headLabel.setTextFill(Color.WHITE);
        AnchorPane.setLeftAnchor(headLabel, 10d);
        AnchorPane.setTopAnchor(headLabel, 10d);            
    }
   
    private void manageNode(final Node NODE) {
        NODE.setManaged(true);
        NODE.setVisible(true);
    }
    private void unManageNode(final Node NODE) {
        NODE.setVisible(false);
        NODE.setManaged(false);
    }
   

    // ******************** Application related *******************************
    @Override public void start(Stage stage) { 
        this.stage=stage;
        Pane pane = new Pane();
        pane.getChildren().addAll(contentPane);
              
        Scene scene = new Scene(pane, SIZE.getWidth(), SIZE.getHeight());        
        scene.getStylesheets().add(Administracion.class.getResource("/vista/styles.css").toExternalForm());
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image("/vista/imagenes/convenios.png"));
        stage.show();      
    }

    public static void main(String[] args) {
        launch(args);
    }
    
     @Override public void stop() {
        System.exit(0);
    }
    
}