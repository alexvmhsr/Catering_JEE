/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;


import com.espe.distribuidas.catering.modelo.Servicio;
import com.espe.distribuidas.catering.servicio.ServicioServicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Vane
 */
@ManagedBean
@ViewScoped
public class ServicioBean implements Serializable {
    
    private List<Servicio> servicios;
    
    private Servicio servicio;
    
    private Servicio servicioSeleccionado;
    
    private String tituloFormulario;
    
    private boolean nuevo;
    
    private boolean enNueva;
    private boolean enModificar;
    private boolean enDetalles;
    
    @EJB
    private ServicioServicio servicioServicio;
        
    @PostConstruct
    public void postConstructor() {
        this.servicios = this.servicioServicio.ObtenerTodas();
    }

    public void nuevoServicio() {
        this.servicio = new Servicio();
        this.enNueva =true;
        this.tituloFormulario = "Creación de Servicio";
    }
    
    public void modificarServicio() {
        if (this.servicioSeleccionado!=null) {
            this.tituloFormulario = "Modificación de Servicio";
            this.copiarServicioSeleccionado();
            this.enModificar = true;
        }
    }
                
    public void detallesServicio() {
        if (this.servicioSeleccionado!=null) {
            this.tituloFormulario = "Detalles de Servicio";
            this.copiarServicioSeleccionado();
            this.enDetalles = true;
        }
    }    
    public void guardarServicio() {
        if (this.enNueva){
            try {
                this.servicioServicio.crearServicio(this.servicio);
                this.enNueva = false;
                this.servicios = this.servicioServicio.ObtenerTodas();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Servicio Creado.", "Se ha creado el "+this.servicio);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear el servicio.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            try {
                this.servicioServicio.ActualizarServicio(this.servicio);
                this.enModificar = false;
                this.servicios= this.servicioServicio.ObtenerTodas();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Servicio Actualizado.", "Se ha actualizado el "+this.servicio);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar el servicio.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
    
    public void eliminarServicio() {
        if (this.servicioSeleccionado != null) {
            try {
                this.copiarServicioSeleccionado();
                this.servicioServicio.eliminarServicio(this.servicio.getCodigo());
                this.servicios= this.servicioServicio.ObtenerTodas();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Servicio Eliminado.", "Se ha eliminado el servicio " + this.servicio);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar el servicio. ", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        }
    }
    
     private void copiarServicioSeleccionado() {
        this.servicio= new Servicio();
        this.servicio.setCodigo(this.servicioSeleccionado.getCodigo());
        this.servicio.setTipoServicio(this.servicioSeleccionado.getTipoServicio());
        this.servicio.setValor(this.servicioSeleccionado.getValor());
        this.servicio.setNombre(this.servicioSeleccionado.getNombre());
        this.servicio.setApellido(this.servicioSeleccionado.getApellido());
        this.servicio.setCorreo(this.servicioSeleccionado.getCorreo());
        
    }
     
    public void cancelar() {
        this.enNueva=false;
        this.enModificar = false;
        this.enDetalles = false;
    }
    
    public void selectClienteFromDialog(Servicio servicio) {
        RequestContext.getCurrentInstance().closeDialog(servicio);
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

  
        

    public boolean isEnNueva() {
        return enNueva;
    }

    

    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
    }

    public Servicio getServicioSeleccionado() {
        return servicioSeleccionado;
    }

    public void setServicioSeleccionado(Servicio servicioSeleccionado) {
        this.servicioSeleccionado = servicioSeleccionado;
    }
            
    public String getTituloFormulario() {
        return tituloFormulario;
    }

    public boolean isEnModificar() {
        return enModificar;
    }

    public boolean isEnDetalles() {
        return enDetalles;
    }
    
}
