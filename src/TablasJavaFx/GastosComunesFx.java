package TablasJavaFx;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import modelo.Gastoscomunes;

public class GastosComunesFx {
    
    private SimpleIntegerProperty idGastosComunes;    
    private UnidadFx unidad;
    private SimpleIntegerProperty monto;
    private SimpleBooleanProperty bonificacion;
    private SimpleIntegerProperty estado;
    private SimpleStringProperty fechaPago;
    private SimpleStringProperty periodo;
     
     public GastosComunesFx(){
     
     }
     
     public GastosComunesFx(Gastoscomunes gastoscomunes){
         this.idGastosComunes.set(gastoscomunes.getIdGastosComunes());
         this.monto.set(gastoscomunes.getMonto_1());
         this.bonificacion.set(gastoscomunes.getBonificacion());
         this.estado.set(gastoscomunes.getEstado());
         this.fechaPago.set(gastoscomunes.getFechaPago().toString());
         this.periodo.set(gastoscomunes.getPeriodo());
     }     

    public int getIdGastosComunes() {
        return idGastosComunes.get();
    }

    public void setIdGastosComunes(int idGastosComunes) {
        this.idGastosComunes.set(idGastosComunes);
    }

    public UnidadFx getUnidad() {
        return unidad;
    }

    public void setUnidad(UnidadFx unidad) {
        this.unidad=unidad;
    }

    public int getMonto() {
        return monto.get();
    }

    public void setMonto(int monto) {
        this.monto.set(monto);
    }

    public boolean getBonificacion() {
        return bonificacion.get();
    }

    public void setBonificacion(boolean bonificacion) {
        this.bonificacion.set(bonificacion);
    }

    public int getEstado() {
        return estado.get();
    }

    public void setEstado(int estado) {
        this.estado.set(estado);
    }

    public String getFechaPago() {
        return fechaPago.get();
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago.set(fechaPago);
    }

    public String getPeriodo() {
        return periodo.get();
    }

    public void setPeriodo(String periodo) {
        this.periodo.set(periodo);
    }
     
     
     
}
