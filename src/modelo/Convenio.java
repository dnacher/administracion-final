package modelo;
// Generated 30/08/2016 05:05:39 PM by Hibernate Tools 4.3.1



/**
 * Convenio generated by hbm2java
 */
public class Convenio  implements java.io.Serializable {


     private int idConvenio;
     private Monto monto;
     private Tipobonificacion tipobonificacion;
     private Unidad unidad;
     private Integer deudaTotal;
     private Boolean tieneBonificacion;
     private Integer cuotas;
     private Boolean activo;
     private Integer saldoInicial;
     private String descripcion;

    public Convenio() {
    }

	
    public Convenio(int idConvenio, Monto monto, Tipobonificacion tipobonificacion, Unidad unidad) {
        this.idConvenio = idConvenio;
        this.monto = monto;
        this.tipobonificacion = tipobonificacion;
        this.unidad = unidad;
    }
    public Convenio(int idConvenio, Monto monto, Tipobonificacion tipobonificacion, Unidad unidad, Integer deudaTotal, Boolean tieneBonificacion, Integer cuotas, Boolean activo, Integer saldoInicial, String descripcion) {
       this.idConvenio = idConvenio;
       this.monto = monto;
       this.tipobonificacion = tipobonificacion;
       this.unidad = unidad;
       this.deudaTotal = deudaTotal;
       this.tieneBonificacion = tieneBonificacion;
       this.cuotas = cuotas;
       this.activo = activo;
       this.saldoInicial = saldoInicial;
       this.descripcion = descripcion;
    }
   
    public int getIdConvenio() {
        return this.idConvenio;
    }
    
    public void setIdConvenio(int idConvenio) {
        this.idConvenio = idConvenio;
    }
    public Monto getMonto() {
        return this.monto;
    }
    
    public void setMonto(Monto monto) {
        this.monto = monto;
    }
    public Tipobonificacion getTipobonificacion() {
        return this.tipobonificacion;
    }
    
    public void setTipobonificacion(Tipobonificacion tipobonificacion) {
        this.tipobonificacion = tipobonificacion;
    }
    public Unidad getUnidad() {
        return this.unidad;
    }
    
    public void setUnidad(Unidad unidad) {
        this.unidad = unidad;
    }
    public Integer getDeudaTotal() {
        return this.deudaTotal;
    }
    
    public void setDeudaTotal(Integer deudaTotal) {
        this.deudaTotal = deudaTotal;
    }
    public Boolean getTieneBonificacion() {
        return this.tieneBonificacion;
    }
    
    public void setTieneBonificacion(Boolean tieneBonificacion) {
        this.tieneBonificacion = tieneBonificacion;
    }
    public Integer getCuotas() {
        return this.cuotas;
    }
    
    public void setCuotas(Integer cuotas) {
        this.cuotas = cuotas;
    }
    public Boolean getActivo() {
        return this.activo;
    }
    
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    public Integer getSaldoInicial() {
        return this.saldoInicial;
    }
    
    public void setSaldoInicial(Integer saldoInicial) {
        this.saldoInicial = saldoInicial;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }




}


