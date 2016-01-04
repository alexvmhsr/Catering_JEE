/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

import com.espe.distribuidas.catering.modelo.Alimentacion;
import com.espe.distribuidas.catering.servicio.AlimentacionServicio;
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
public class AlimentacionBean implements Serializable {
    
    private List<Alimentacion> listaAlimentacion;
    
    private Alimentacion alimentacion;
    
    private Alimentacion alimentoSeleccionado;
    
    private String tituloFormulario;
    
    private boolean nuevo;
    
    private boolean enNueva;
    private boolean enModificar;
    private boolean enDetalles;
    
    @EJB
    private AlimentacionServicio alimentacionServicio;
    
    @PostConstruct
    public void postConstructor() {
        this.listaAlimentacion = this.alimentacionServicio.ObtenerAlimentacion();
     
    }

    public AlimentacionServicio getAlimentacionServicio() {
        return alimentacionServicio;
    }

    public void setAlimentacionServicio(AlimentacionServicio alimentacionServicio) {
        this.alimentacionServicio = alimentacionServicio;
    }
    
    public void nuevoAlimento() {
        this.alimentacion = new Alimentacion();
        this.enNueva =true;
        this.tituloFormulario = "Creación de Alimento";
    }
    
    public void modificarAlimento() {
        if (this.alimentoSeleccionado!=null) {
            this.tituloFormulario = "Modificación de Alimentación";
            this.copiarAlimentacionSeleccionado();
            this.enModificar = true;
        }
    }
    
    public void detallesAlimento() {
        if (this.alimentoSeleccionado!=null) {
            this.tituloFormulario = "Detalles de Alimentacion";
            this.copiarAlimentacionSeleccionado();
            this.enDetalles = true;
        }
    }
    
    public void guardarCliente() {
        if (this.enNueva) {
            try {
                this.alimentacionServicio.crearAlimentacion(this.alimentacion);
                this.enNueva = false;
                this.listaAlimentacion = this.alimentacionServicio.ObtenerAlimentacion();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alimento Creado.", "Se ha creado el "+this.alimentacion);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear alimentacion.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            try {
                this.alimentacionServicio.ActualizarAlimentacion(this.alimentacion);
                this.enModificar = false;
                this.listaAlimentacion = this.alimentacionServicio.ObtenerAlimentacion();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alimento Actualizado .", "Se ha actualizado el "+this.alimentacion);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar la alimentacion.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
    
    public void eliminarAlimento() {
        if (this.alimentoSeleccionado != null) {
            try {
                this.copiarAlimentacionSeleccionado();
                this.alimentacionServicio.eliminarAlimentacion(this.alimentacion.getCodigo());
                this.listaAlimentacion= this.alimentacionServicio.ObtenerAlimentacion();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alimento eliminado.", "Se ha eliminado el alimento " + this.alimentacion);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar alimentacion. ", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        }
    }
    
    private void copiarAlimentacionSeleccionado() {
        this.alimentacion = new Alimentacion();
        this.alimentacion.setCodigo(this.alimentoSeleccionado.getCodigo());
        this.alimentacion.setNombre(this.alimentoSeleccionado.getNombre());
        this.alimentacion.setCategoria(this.alimentoSeleccionado.getCategoria());
        this.alimentacion.setValor(this.alimentoSeleccionado.getValor());
        this.alimentacion.setDetalle(this.alimentoSeleccionado.getDetalle());
        
    }
    
    public void cancelar() {
        this.enNueva=false;
        this.enModificar = false;
        this.enDetalles = false;
    }
    
    public void selectAlimentacionFromDialog(Alimentacion alimentacion) {
        RequestContext.getCurrentInstance().closeDialog(alimentacion);
    }

    public Alimentacion getAlimentacion() {
        return alimentacion;
    }

    public void setAlimentacion(Alimentacion alimentacion) {
        this.alimentacion = alimentacion;
    }

    public Alimentacion getAlimentoSeleccionado() {
        return alimentoSeleccionado;
    }

    public void setAlimentoSeleccionado(Alimentacion alimentoSeleccionado) {
        this.alimentoSeleccionado = alimentoSeleccionado;
    }

    public boolean isEnNueva() {
        return enNueva;
    }

    public void setEnNueva(boolean enNueva) {
        this.enNueva = enNueva;
    }

    public List<Alimentacion> getListaAlimentacion() {
        return listaAlimentacion;
    }
    public boolean isNuevo() {
        return nuevo;
    }

    public void setNuevo(boolean nuevo) {
        this.nuevo = nuevo;
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
