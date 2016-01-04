/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

import com.espe.distribuidas.catering.modelo.Paquete;
import com.espe.distribuidas.catering.servicio.PaqueteServicio;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import static javax.sql.rowset.spi.SyncFactory.getLogger;
import javax.sql.rowset.spi.SyncFactoryException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author PabloA
 */
@ManagedBean
@ViewScoped
public class PaqueteBean implements Serializable{

    /**
     * Creates a new instance of PaqueteBean
     */
private List<Paquete> paquetes;
    private Paquete paquete;
    
    private Paquete paqueteSeleccionado;
    
    private String tituloFormulario;
    private boolean nuevo;
    
    private boolean enNueva;
    private boolean enModificar;
    private boolean enDetalles;
    

    
    @EJB
    private PaqueteServicio paqueteServicio;
        
    @PostConstruct
    public void postConstructor() {
        this.paquetes = this.paqueteServicio.obtenerTodas();
    }

    public void nuevoPaquete() {
        this.paquete = new Paquete();
        this.enNueva =true;
        this.tituloFormulario = "Creación de Servicio";
    }
    
    public void modificarPaquete() {
        if (this.paqueteSeleccionado!=null) {
            this.tituloFormulario = "Modificación de Servicio";
            this.copiarPaqueteSeleccionado();
            this.enModificar = true;
        }
    }
                
    public void detallesPaquete() {
        if (this.paqueteSeleccionado!=null) {
            this.tituloFormulario = "Detalles de Servicio";
            this.copiarPaqueteSeleccionado();
            this.enDetalles = true;
        }
    }    
    public void guardarPaquete() {
        if (this.enNueva){
            try {
                this.paqueteServicio.crearPaquete(this.paquete);
                this.enNueva = false;
                this.paquetes = this.paqueteServicio.obtenerTodas();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Factura Creado.", "Se ha creado la "+this.paquete);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear la factura.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            try {
                this.paqueteServicio.actualizarPaquete(this.paquete);
                this.enModificar = false;
                this.paquetes= this.paqueteServicio.obtenerTodas();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Factura Actualizada.", "Se ha actualizado el "+this.paquete);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar la factura.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
    
    public void eliminarPaquete() {
        if (this.paqueteSeleccionado != null) {
            try {
                this.copiarPaqueteSeleccionado();
                this.paqueteServicio.eliminarPaquete(this.paquete.getCodigo());
                this.paquetes= this.paqueteServicio.obtenerTodas();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Factura Eliminada.", "Se ha eliminado el servicio " + this.paquete);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar la factura. ", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        }
    }
    
    
     private void copiarPaqueteSeleccionado() {
        this.paquete= new Paquete();
        this.paquete.setCodigo(this.paqueteSeleccionado.getCodigo());
        this.paquete.setNombre(this.paqueteSeleccionado.getNombre());
        this.paquete.setActivo(this.paqueteSeleccionado.getActivo());
        this.paquete.setItems(this.paqueteSeleccionado.getItems());
        this.paquete.setPrecio(this.paqueteSeleccionado.getPrecio());
    }
     
    public void cancelar() {
        this.enNueva=false;
        this.enModificar = false;
        this.enDetalles = false;
    }
    
    public void selectClienteFromDialog(Paquete paquete) {
        RequestContext.getCurrentInstance().closeDialog(paquete);
    }

    public List<Paquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(List<Paquete> paquetes) {
        this.paquetes = paquetes;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public Paquete getPaqueteSeleccionado() {
        return paqueteSeleccionado;
    }

    public void setPaqueteSeleccionado(Paquete paqueteSeleccionado) {
        this.paqueteSeleccionado = paqueteSeleccionado;
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

    public PaqueteServicio getPaqueteServicio() {
        return paqueteServicio;
    }

    public void setPaqueteServicio(PaqueteServicio paqueteServicio) {
        this.paqueteServicio = paqueteServicio;
    }

   

   
   
    
}
   
