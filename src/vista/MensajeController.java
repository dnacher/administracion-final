package vista;

import eu.hansolo.enzo.notification.Notification;
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
            Image img=TraeIcono(imagen);
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
    
    public Image TraeIcono(String icono){
        switch(icono){
            case "error":
               return Notification.ERROR_ICON;
                // return "/vista/imagenes/errorIcon.png";
            case "information":
                return Notification.INFO_ICON;
                 //return "/vista/imagenes/informationIcon.png";
            case "question":
                return Notification.WARNING_ICON;
                 //return "/vista/imagenes/questionIcon.png";
            case "warning":
                return Notification.WARNING_ICON;
                 //return "/vista/imagenes/warningIcon.png";
            case "tick":
               return Notification.SUCCESS_ICON;
                //return "/vista/imagenes/Tick.png";
            default:
                return Notification.ERROR_ICON;
                 //return "/vista/imagenes/errorIcon.png";        
        }
        
     }
    
}
