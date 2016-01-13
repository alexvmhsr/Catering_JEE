/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

import com.espe.distribuidas.catering.modelo.TipoEvento;
import com.espe.distribuidas.catering.servicio.TipoEventoServicio;
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
public class TipoEventoBean implements Serializable{
    
     @EJB
     private TipoEventoServicio tipoEventoServicio;
     
     private List<TipoEvento> ListaTipoEvento;
     private TipoEvento tipoEvento;
     private TipoEvento tipoEventoSeleccionado;

     private String tituloFormulario;
     
     private boolean nuevo;
     private boolean enNueva;
     private boolean enModificar;
     private boolean enDetalles;

     
     @PostConstruct
     public void inciar() {
         this.ListaTipoEvento = this.tipoEventoServicio.obtenerTipoEventos();
     }
   
      public void nuevoTipoEvento() {
        this.tipoEvento = new TipoEvento();
        this.enNueva = true;
        this.tituloFormulario = "Creación de Tipo de Mobiliario";
    }

    public void modificarTipoEvento() {
        if (this.tipoEventoSeleccionado != null) {
            this.tituloFormulario = "Modificación de Tipo de Mobiliario";
            this.copiarTipoEventoSeleccionado();
            this.enModificar = true;
        }
    }

    public void detallesTipoEvento() {
        if (this.tipoEventoSeleccionado != null) {
            this.tituloFormulario = "Detalles de Tipo de Mobiliario";
            this.copiarTipoEventoSeleccionado();
            this.enDetalles = true;
        }
    }

    public void guardarTipoEvento() {
        if (this.enNueva) {
            try {
                this.tipoEventoServicio.crearTipoEveto(tipoEvento);
                this.enNueva = false;
                this.ListaTipoEvento = this.tipoEventoServicio.obtenerTipoEventos();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de Evento Creado.", "Se ha creado el " + this.tipoEvento);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear el tipo de evento.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            try {
                this.tipoEventoServicio.ActualizarTipoEvento(tipoEvento);
                this.enModificar = false;
                this.ListaTipoEvento = this.tipoEventoServicio.obtenerTipoEventos();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado tipo Evento actualizado.", "Se ha actualizado el " + this.tipoEvento);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar tipo evento.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
    public void eliminarTipoEvento() {
        if (this.tipoEventoSeleccionado != null) {
            try {
                this.copiarTipoEventoSeleccionado();
                this.tipoEventoServicio.eliminarTipoEvento(this.tipoEvento.getCodigo());
                this.ListaTipoEvento = this.tipoEventoServicio.obtenerTipoEventos();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de Evento Eliminado.", "Se ha eliminado el tipo " + this.tipoEvento);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar el tipo de evento. ", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        }
    }
    private void copiarTipoEventoSeleccionado() {
        this.tipoEvento = new TipoEvento();
        this.tipoEvento.setCodigo(this.tipoEventoSeleccionado.getCodigo());
        this.tipoEvento.setNombre(this.tipoEventoSeleccionado.getNombre());
        this.tipoEvento.setDetalle(this.tipoEventoSeleccionado.getDetalle());

    }

    public void cancelar() {
        this.enNueva = false;
        this.enModificar = false;
        this.enDetalles = false;
    }

    

    public void selectTipoEventoFromDialog(TipoEvento tmpTipoEvento) {
        RequestContext.getCurrentInstance().closeDialog(tmpTipoEvento);
    }

    public TipoEventoServicio getTipoEventoServicio() {
        return tipoEventoServicio;
    }

    public void setTipoEventoServicio(TipoEventoServicio tipoEventoServicio) {
        this.tipoEventoServicio = tipoEventoServicio;
    }

    public List<TipoEvento> getListaTipoEvento() {
        return ListaTipoEvento;
    }

    public void setListaTipoEvento(List<TipoEvento> ListaTipoEvento) {
        this.ListaTipoEvento = ListaTipoEvento;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public TipoEvento getTipoEventoSeleccionado() {
        return tipoEventoSeleccionado;
    }

    public void setTipoEventoSeleccionado(TipoEvento tipoEventoSeleccionado) {
        this.tipoEventoSeleccionado = tipoEventoSeleccionado;
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
    
}
