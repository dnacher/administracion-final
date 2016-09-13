package control;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import traynotification.notification.Notification;
import traynotification.notification.Notifications;
import traynotification.notification.TrayNotification;
import vista.MensajeController;

public class ControlVentana {
    
    public void crearVentanasinCSS(String nombreVentana, String tituloVentana) throws IOException{
		Stage primaryStage= new Stage();
                primaryStage.getIcons().add(new Image("/vista/imagenes/convenios.png"));
		Pane root= FXMLLoader.load(getClass().getResource("/vista/" + nombreVentana + ".fxml"));		
		Scene scene= new Scene(root);		
		primaryStage.setTitle(tituloVentana);
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
    
     public void crearVentanaMain(String nombreVentana, String tituloVentana) throws IOException{
		try {
                        Stage primaryStage= new Stage();
                        primaryStage.getIcons().add(new Image("/vista/imagenes/convenios.png"));
			Parent root = FXMLLoader.load(getClass().getResource("/vista/" + nombreVentana + ".fxml"));
			Scene scene= new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/vista/styles.css").toExternalForm());
			primaryStage.setTitle(tituloVentana);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
                    System.out.println(e.getMessage());
		}	
	}
     
     public void creaVentanaError(String mensaje, String imagen){
     
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Mensaje.fxml"));
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.getIcons().add(new Image("/vista/imagenes/convenios.png"));
        try {
            stage.setScene(
                    new Scene((Pane) loader.load()));
        } catch (IOException ex) {
            Logger.getLogger(ControlVentana.class.getName()).log(Level.SEVERE, null, ex);
        }
         MensajeController controller = loader.<MensajeController>getController();
        controller.initData(mensaje, imagen);
        stage.show();
     } 
     
     public void creaVentanaNotificacion(String titulo, String mensaje, int segundosMensaje, String icono){
        Notification Notif=traeIcono(icono);
        TrayNotification tn=new TrayNotification(titulo, mensaje, Notif);
        tn.showAndDismiss(Duration.seconds(segundosMensaje));
     }
     
     public Notification traeIcono(String icono){
         Notification notif;
         switch(icono){
            case "error":
              notif=Notifications.ERROR;
              break;  
            case "information":
              notif=Notifications.INFORMATION;
              break; 
            case "question":
              notif=Notifications.QUESTION;
              break;  
            case "warning":
              notif=Notifications.WARNING;
              break;  
            case "tick":
              notif=Notifications.SUCCESS;
              break; 
            default:
              notif=Notifications.ERROR;
              break;          
         }
         return notif;
     }
    
}
