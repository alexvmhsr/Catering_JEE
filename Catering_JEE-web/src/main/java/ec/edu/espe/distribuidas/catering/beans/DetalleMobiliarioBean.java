/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

import com.espe.distribuidas.catering.modelo.DetalleMobiliario;
import com.espe.distribuidas.catering.modelo.Mobiliario;
import com.espe.distribuidas.catering.modelo.Paquete;
import com.espe.distribuidas.catering.servicio.MobiliarioServicio;
import com.espe.distribuidas.catering.servicio.PaqueteServicio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author PabloA
 */
@ManagedBean
@ViewScoped
public class DetalleMobiliarioBean implements Serializable {

    /**
     * Creates a new instance of DetalleMobiliarioBean
     */
  

    private List<DetalleMobiliario> detMobiliarios;
    private DetalleMobiliario detMobiliario;
    private DetalleMobiliario detMobiliarioSeleccionado;

    private List<Mobiliario> mobiliarios;
    private Mobiliario mobiliario;
    private Mobiliario mobiliarioSeleccionado;

    private List<Paquete> paquetes;
    private Paquete paquete;
    private Paquete paqueteSeleccionado;

    private String tituloFormulario;

    private boolean enNueva;
    private boolean enModificar;
    private boolean enDetalles;

    @EJB
    private MobiliarioServicio mobiliarioServicio;

    @EJB
    private PaqueteServicio paqueteServicio;

    @PostConstruct
    public void postConstructor() {
        this.detMobiliarios = this.mobiliarioServicio.obtenerTodasDetalleMobiliario();
    }

    public void nuevoDetalle() {
        this.detMobiliario = new DetalleMobiliario();
        this.enNueva = true;
        this.tituloFormulario = "Añadir al Paquete";
    }

    public void modificarDetalle() {
        if (this.detMobiliarioSeleccionado != null) {
            this.tituloFormulario = "Modificar";
            this.copiarDetalleSeleccionado();
            this.enModificar = true;
        }
    }

    public void detallesMobiliario() {
        if (this.detMobiliarioSeleccionado != null) {
            this.tituloFormulario = "Detalles Selección";
            this.copiarDetalleSeleccionado();
            this.enDetalles = true;
        }
    }

    private void copiarDetalleSeleccionado() {
       

    }

    public void cancelar() {
        this.enNueva = false;
        this.enModificar = false;
        this.enDetalles = false;
    }

    public void guardarDetalle() {
        if (this.enNueva) {
            try {
//             
                this.mobiliarioServicio.crearDetalleMobiliario(this.detMobiliario);
                this.enNueva = false;
                this.detMobiliarios = this.mobiliarioServicio.obtenerTodasDetalleMobiliario();
                this.mobiliarios = this.mobiliarioServicio.obtenerTodasMobiliario();
                this.paquetes = this.paqueteServicio.obtenerTodas();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Detalle Creado.", "Se ha creado el " + this.detMobiliario);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear Detalle.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            try {
               
                this.mobiliarioServicio.actualizarDetalleMobiliario(this.detMobiliario);
                this.enModificar = false;
                this.detMobiliarios = this.mobiliarioServicio.obtenerTodasDetalleMobiliario();
                this.mobiliarios = this.mobiliarioServicio.obtenerTodasMobiliario();
                this.paquetes = this.paqueteServicio.obtenerTodas();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Detalle Actualizado.", "Se ha actualizado el " + this.detMobiliario);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar detalle.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public List<DetalleMobiliario> getDetMobiliarios() {
        return detMobiliarios;
    }

    public void setDetMobiliarios(List<DetalleMobiliario> detMobiliarios) {
        this.detMobiliarios = detMobiliarios;
    }

    public DetalleMobiliario getDetMobiliario() {
        return detMobiliario;
    }

    public void setDetMobiliario(DetalleMobiliario detMobiliario) {
        this.detMobiliario = detMobiliario;
    }

    public DetalleMobiliario getDetMobiliarioSeleccionado() {
        return detMobiliarioSeleccionado;
    }

    public void setDetMobiliarioSeleccionado(DetalleMobiliario detMobiliarioSeleccionado) {
        this.detMobiliarioSeleccionado = detMobiliarioSeleccionado;
    }

    public List<Mobiliario> getMobiliarios() {
        return mobiliarios;
    }

    public void setMobiliarios(List<Mobiliario> mobiliarios) {
        this.mobiliarios = mobiliarios;
    }

    public Mobiliario getMobiliario() {
        return mobiliario;
    }

    public void setMobiliario(Mobiliario mobiliario) {
        this.mobiliario = mobiliario;
    }

    public Mobiliario getMobiliarioSeleccionado() {
        return mobiliarioSeleccionado;
    }

    public void setMobiliarioSeleccionado(Mobiliario mobiliarioSeleccionado) {
        this.mobiliarioSeleccionado = mobiliarioSeleccionado;
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

    public MobiliarioServicio getMobiliarioServicio() {
        return mobiliarioServicio;
    }

    public void setMobiliarioServicio(MobiliarioServicio mobiliarioServicio) {
        this.mobiliarioServicio = mobiliarioServicio;
    }

    public PaqueteServicio getPaqueteServicio() {
        return paqueteServicio;
    }

    public void setPaqueteServicio(PaqueteServicio paqueteServicio) {
        this.paqueteServicio = paqueteServicio;
    }

}
