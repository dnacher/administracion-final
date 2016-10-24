package TablasJavaFx;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Unidad;

public class UnidadFx {
    
     private SimpleIntegerProperty idUnidad;
     private SimpleStringProperty block;
     private SimpleIntegerProperty torre;
     private SimpleIntegerProperty puerta;
     private SimpleStringProperty nombre;
     private SimpleStringProperty apellido;
     private SimpleStringProperty telefono;
     private SimpleStringProperty mail;
     private SimpleBooleanProperty propietarioInquilino;
     private SimpleStringProperty fechaIngreso;
     private SimpleBooleanProperty activo;
     
     public UnidadFx(){
     
     }
     
     public UnidadFx(Unidad unidad){ 
        this.idUnidad = new SimpleIntegerProperty(unidad.getIdUnidad());        
        this.block=new SimpleStringProperty(unidad.getBlock());
        this.torre=new SimpleIntegerProperty(unidad.getTorre());
        this.puerta=new SimpleIntegerProperty(unidad.getPuerta());
        this.nombre=new SimpleStringProperty(unidad.getNombre());
        this.apellido=new SimpleStringProperty(unidad.getApellido());
        this.telefono=new SimpleStringProperty(unidad.getTelefono());
        this.mail=new SimpleStringProperty(unidad.getMail());
        this.propietarioInquilino=new SimpleBooleanProperty(unidad.getPropietarioInquilino());
        this.fechaIngreso=new SimpleStringProperty(unidad.getFechaIngreso().toString());
        this.activo=new SimpleBooleanProperty(unidad.getActivo());
     }
     
     public Unidad devuelveUnidad(UnidadFx unidadfx) throws ParseException{ 
        Unidad unidad= new Unidad();
        unidad.setIdUnidad(unidadfx.getIdUnidad());        
        unidad.setBlock(unidadfx.getBlock());
        unidad.setTorre(unidadfx.getTorre());
        unidad.setPuerta(unidadfx.getPuerta());
        unidad.setNombre(unidadfx.getNombre());
        unidad.setApellido(unidadfx.getApellido());
        unidad.setTelefono(unidadfx.getTelefono());
        unidad.setMail(unidadfx.getMail());
        unidad.setPropietarioInquilino(unidadfx.getPropietarioInquilino());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(unidadfx.getFechaIngreso());
        unidad.setFechaIngreso(date);
        unidad.setActivo(unidadfx.getActivo());
        return unidad;
     }

    public int getIdUnidad() {
        return idUnidad.get();
    }

    public void setIdUnidad(int idUnidad) {
        this.idUnidad.set(idUnidad);
    }

    public String getBlock() {
        return block.get();
    }

    public void setBlock(String block) {
        this.block.set(block);
    }

    public int getTorre() {
        return torre.get();
    }

    public void setTorre(int torre) {
        this.torre.set(torre);
    }

    public int getPuerta() {
        return puerta.get();
    }

    public void setPuerta(int puerta) {
        this.puerta.set(puerta);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public String getTelefono() {
        return telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public String getMail() {
        return mail.get();
    }

    public void setMail(String mail) {
        this.mail.set(mail);
    }

    public boolean getPropietarioInquilino() {
        return propietarioInquilino.get();
    }

    public void setPropietarioInquilino(boolean propietarioInquilino) {
        this.propietarioInquilino.set(propietarioInquilino);
    }

    public String getFechaIngreso() {
       return fechaIngreso.get();      
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso.set(fechaIngreso.toString());
    }

    public boolean getActivo() {
        return activo.get();
    }

    public void setActivo(boolean activo) {
        this.activo.set(activo);
    }
     
    public ObservableList<UnidadFx> getLista(List<Unidad> lista){
        List<UnidadFx> li=new ArrayList<>();
        for(Unidad u: lista){
        
            UnidadFx fx=new UnidadFx(u);
            li.add(fx);
        }        
        ObservableList<UnidadFx> retorno = FXCollections.observableList(li);       
        return retorno;
     }
     
}
