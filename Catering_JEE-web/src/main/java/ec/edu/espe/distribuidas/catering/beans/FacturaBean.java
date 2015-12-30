/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

import com.espe.distribuidas.catering.modelo.Factura;
import com.espe.distribuidas.catering.servicio.FacturaServicio;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author PabloA
 */
@ManagedBean
@ViewScoped
public class FacturaBean implements Serializable{

    private List<Factura> facturas;
    private Factura factura;
    
    private Factura facturaSeleccionado;
    
    private String tituloFormulario;
    private String cliente;
    private boolean nuevo;
    
    private boolean enNueva;
    private boolean enModificar;
    private boolean enDetalles;
    
    @EJB
    private FacturaServicio facturaServicio;
        
    @PostConstruct
    public void postConstructor() {
        this.facturas = this.facturaServicio.obtenerTodas();
    }

    public void nuevoFactura() {
        this.factura = new Factura();
        this.enNueva =true;
        this.tituloFormulario = "Creación de Servicio";
    }
    
    public void modificarFactura() {
        if (this.facturaSeleccionado!=null) {
            this.tituloFormulario = "Modificación de Servicio";
            this.copiarFacturaSeleccionado();
            this.enModificar = true;
        }
    }
                
    public void detallesFactura() {
        if (this.facturaSeleccionado!=null) {
            this.tituloFormulario = "Detalles de Servicio";
            this.copiarFacturaSeleccionado();
            this.enDetalles = true;
        }
    }    
    public void guardarFactura() {
        if (this.enNueva){
            try {
                this.facturaServicio.crearFactura(this.factura);
                this.enNueva = false;
                this.facturas = this.facturaServicio.obtenerTodas();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Factura Creado.", "Se ha creado la "+this.factura);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear la factura.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            try {
                this.facturaServicio.actualizarFactura(this.factura);
                this.enModificar = false;
                this.facturas= this.facturaServicio.obtenerTodas();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Factura Actualizada.", "Se ha actualizado el "+this.factura);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar la factura.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
    
    public void eliminarFactura() {
        if (this.facturaSeleccionado != null) {
            try {
                this.copiarFacturaSeleccionado();
                this.facturaServicio.eliminarFactura(this.factura.getCodigo());
                this.facturas= this.facturaServicio.obtenerTodas();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Factura Eliminada.", "Se ha eliminado el servicio " + this.factura);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar la factura. ", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        }
    }
      public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }
     
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
         
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }
    
     private void copiarFacturaSeleccionado() {
        this.factura= new Factura();
        this.factura.setCodigo(this.facturaSeleccionado.getCodigo());
        this.factura.setCodigoCliente(this.facturaSeleccionado.getCodigoCliente());
        this.factura.setFecha(this.facturaSeleccionado.getFecha());
        
    }
     
    public void cancelar() {
        this.enNueva=false;
        this.enModificar = false;
        this.enDetalles = false;
    }
    
    public void selectClienteFromDialog(Factura factura) {
        RequestContext.getCurrentInstance().closeDialog(factura);
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Factura getFacturaSeleccionado() {
        return facturaSeleccionado;
    }

    public void setFacturaSeleccionado(Factura facturaSeleccionado) {
        this.facturaSeleccionado = facturaSeleccionado;
    }

    public String getTituloFormulario() {
        return tituloFormulario;
    }

    public void setTituloFormulario(String tituloFormulario) {
        this.tituloFormulario = tituloFormulario;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public boolean isEnNueva() {
        return enNueva;
    }

    public void setEnNueva(boolean enNueva) {
        this.enNueva = enNueva;
    }

    public boolean isEnModificar() {
        return enModificar;
    }

    public void setEnModificar(boolean enModificar) {
        this.enModificar = enModificar;
    }

    public boolean isEnDetalles() {
        return enDetalles;
    }

    public void setEnDetalles(boolean enDetalles) {
        this.enDetalles = enDetalles;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public FacturaServicio getFacturaServicio() {
        return facturaServicio;
    }

    public void setFacturaServicio(FacturaServicio facturaServicio) {
        this.facturaServicio = facturaServicio;
    }

   
   
    
}
