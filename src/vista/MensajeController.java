package vista;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


public class MensajeController implements Initializable {
    
    @FXML
    private ImageView LblImage;

    @FXML
    private Label TxtInformation;
    
    @FXML
    private Button BtnOk;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }   
    

    public void initData(String mensaje, String imagen) {
        TxtInformation.setText(mensaje);
        try{
        Image img= new Image(TraeIcono(imagen));        
        LblImage.setImage(img);                
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    public void Ok(ActionEvent event) {
        Stage stage = (Stage) BtnOk.getScene().getWindow();
        stage.close();
    }
    
    public String TraeIcono(String icono){
        switch(icono){
            case "error":
                 return "/vista/imagenes/errorIcon.png";
            case "information":
                return "/vista/imagenes/informationIcon.png";
            case "question":
                 return "/vista/imagenes/questionIcon.png";
            case "warning":
                 return "/vista/imagenes/warningIcon.png";
            case "tick":
                return "/vista/imagenes/Tick.png";
            default:
                 return "/vista/imagenes/errorIcon.png";        
        }
        
     }
    
}
