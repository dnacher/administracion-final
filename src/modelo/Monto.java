package modelo;
// Generated 30/08/2016 05:05:39 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Monto generated by hbm2java
 */
public class Monto  implements java.io.Serializable {


     private int idMonto;
     private String tipoMonto;
     private Integer valorPesos;
     private Boolean activo;
     private Date fechaActualizacion;
     private Set convenios = new HashSet(0);
     private Set gastoscomuneses = new HashSet(0);
     private Set otrosgastoses = new HashSet(0);
     private Set notacreditos = new HashSet(0);

    public Monto() {
    }

	
    public Monto(int idMonto) {
        this.idMonto = idMonto;
    }
    public Monto(int idMonto, String tipoMonto, Integer valorPesos, Boolean activo, Date fechaActualizacion, Set convenios, Set gastoscomuneses, Set otrosgastoses, Set notacreditos) {
       this.idMonto = idMonto;
       this.tipoMonto = tipoMonto;
       this.valorPesos = valorPesos;
       this.activo = activo;
       this.fechaActualizacion = fechaActualizacion;
       this.convenios = convenios;
       this.gastoscomuneses = gastoscomuneses;
       this.otrosgastoses = otrosgastoses;
       this.notacreditos = notacreditos;
    }
   
    public int getIdMonto() {
        return this.idMonto;
    }
    
    public void setIdMonto(int idMonto) {
        this.idMonto = idMonto;
    }
    public String getTipoMonto() {
        return this.tipoMonto;
    }
    
    public void setTipoMonto(String tipoMonto) {
        this.tipoMonto = tipoMonto;
    }
    public Integer getValorPesos() {
        return this.valorPesos;
    }
    
    public void setValorPesos(Integer valorPesos) {
        this.valorPesos = valorPesos;
    }
    public Boolean getActivo() {
        return this.activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    public Date getFechaActualizacion() {
        return this.fechaActualizacion;
    }
    
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    public Set getConvenios() {
        return this.convenios;
    }
    
    public void setConvenios(Set convenios) {
        this.convenios = convenios;
    }
    public Set getGastoscomuneses() {
        return this.gastoscomuneses;
    }
    
    public void setGastoscomuneses(Set gastoscomuneses) {
        this.gastoscomuneses = gastoscomuneses;
    }
    public Set getOtrosgastoses() {
        return this.otrosgastoses;
    }
    
    public void setOtrosgastoses(Set otrosgastoses) {
        this.otrosgastoses = otrosgastoses;
    }
    public Set getNotacreditos() {
        return this.notacreditos;
    }
    
    public void setNotacreditos(Set notacreditos) {
        this.notacreditos = notacreditos;
    }




}


