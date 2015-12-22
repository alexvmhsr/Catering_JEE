/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

import com.espe.distribuidas.catering.modelo.TipoMobiliario;
import com.espe.distribuidas.catering.servicio.TipoMobiliarioServicio;
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
 * @author PabloA
 */
@ManagedBean
@ViewScoped
public class TipomobiliarioBean implements Serializable {

    /**
     * Creates a new instance of TipomobiliarioBean
     */
    @EJB
    private TipoMobiliarioServicio tmServicio;

    private List<TipoMobiliario> tms;

    private TipoMobiliario tm;

    private TipoMobiliario tmSeleccionado;

    private String tituloFormulario;
 
    private boolean nuevo;

    private boolean enNueva;
    private boolean enModificar;
    private boolean enDetalles;

    @PostConstruct
    public void init() {
        this.tms = this.tmServicio.obtenerTipoMobiliario();
    }

    public TipoMobiliarioServicio getTmServicio() {
        return tmServicio;
    }

    public void setTmServicio(TipoMobiliarioServicio tmServicio) {
        this.tmServicio = tmServicio;
    }

    public List<TipoMobiliario> getTms() {
        return tms;
    }

    public void setTms(List<TipoMobiliario> tms) {
        this.tms = tms;
    }

    public TipoMobiliario getTm() {
        return tm;
    }

    public void setTm(TipoMobiliario tm) {
        this.tm = tm;
    }

    public TipoMobiliario getTmSeleccionado() {
        return tmSeleccionado;
    }

    public void setTmSeleccionado(TipoMobiliario tmSeleccionado) {
        this.tmSeleccionado = tmSeleccionado;
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

    public void nuevoTipoMobiliario() {
        this.tm = new TipoMobiliario();
        this.enNueva = true;
        this.tituloFormulario = "Creación de Tipo de Mobiliario";
    }

    public void modificarTipoMobiliario() {
        if (this.tmSeleccionado != null) {
            this.tituloFormulario = "Modificación de Tipo de Mobiliario";
            this.copiarTipoMobiliarioSeleccionado();
            this.enModificar = true;
        }
    }

    public void detallesTipoMobiliario() {
        if (this.tmSeleccionado != null) {
            this.tituloFormulario = "Detalles de Tipo de Mobiliario";
            this.copiarTipoMobiliarioSeleccionado();
            this.enDetalles = true;
        }
    }

    public void guardarTipoMobiliario() {
        if (this.enNueva) {
            try {
                this.tmServicio.crearTipoMobiliario(this.tm);
                this.enNueva = false;
                this.tms = this.tmServicio.obtenerTipoMobiliario();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de Mobiliario Creado.", "Se ha creado el " + this.tm);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear el tipo de mobiliario.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            try {
                this.tmServicio.actualizarTipoMobiliario(this.tm);
                this.enModificar = false;
                this.tms = this.tmServicio.obtenerTipoMobiliario();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Actualizado.", "Se ha actualizado el " + this.tm);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar cliente.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
    public void eliminarTipoMobiliario() {
        if (this.tmSeleccionado != null) {
            try {
                this.copiarTipoMobiliarioSeleccionado();
                this.tmServicio.eliminarTipoMobiliario(this.tm.getCodigo());
                this.tms = this.tmServicio.obtenerTipoMobiliario();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Tipo de Mobiliario Eliminado.", "Se ha eliminado el tipo " + this.tm);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar el tipo. ", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        }
    }
    private void copiarTipoMobiliarioSeleccionado() {
        this.tm = new TipoMobiliario();
        this.tm.setCodigo(this.tmSeleccionado.getCodigo());
        this.tm.setNombre(this.tmSeleccionado.getNombre());
        this.tm.setDescripcion(this.tmSeleccionado.getDescripcion());

    }

    public void cancelar() {
        this.enNueva = false;
        this.enModificar = false;
        this.enDetalles = false;
    }

    

    public void selectTipoMobiliarioFromDialog(TipoMobiliario tmpTipoMobiliario) {
        RequestContext.getCurrentInstance().closeDialog(tmpTipoMobiliario);
    }

    
}
