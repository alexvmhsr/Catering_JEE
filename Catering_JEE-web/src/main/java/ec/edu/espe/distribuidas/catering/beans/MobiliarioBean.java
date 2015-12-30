/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

import com.espe.distribuidas.catering.modelo.Mobiliario;
import com.espe.distribuidas.catering.modelo.TipoMobiliario;
import com.espe.distribuidas.catering.servicio.MobiliarioServicio;
import com.espe.distribuidas.catering.servicio.TipoMobiliarioServicio;
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
 * @author Vane
 */

@ManagedBean
@ViewScoped
public class MobiliarioBean implements Serializable{
   
     private List<Mobiliario> listaMobiliario;
     private Mobiliario mobiliario;
     private Mobiliario mobiliarioSeleccionado;
     
     private List<TipoMobiliario> listaTipoMobiliario;
     private String tipoMobiliario;
     private TipoMobiliario tipomobiliarioSeleccionado;
     
     
     private String tituloFormulario;
     
              
     private boolean nuevo;
     
     private boolean enNueva;
     
     private boolean enModificar;
     
     private boolean enDetalles;
     
     @EJB
     private MobiliarioServicio mobiliarioServicio;
     
     @EJB
     private TipoMobiliarioServicio tipomobiliarioServicio;
         
     
     @PostConstruct
     
     public void postConstructor() {
         
       this.listaMobiliario = this.mobiliarioServicio.obtenerTodasMobiliario();
       this.listaTipoMobiliario= this.tipomobiliarioServicio.obtenerTipoMobiliario();
        
    }

    public MobiliarioServicio getMobiliarioServicio() {
        return mobiliarioServicio;
    }

    public void setMobiliarioServicio(MobiliarioServicio mobiliarioServicio) {
        this.mobiliarioServicio = mobiliarioServicio;
    }
    
    
    public void nuevoMobiliario() {
        this.mobiliario = new Mobiliario();
        this.enNueva =true;
        this.tituloFormulario = "Creación de Mobiliario";
    }
    
    public void modificarMobiliario() {
        if (this.mobiliarioSeleccionado!=null) {
            this.tituloFormulario = "Modificación de Mobiliario";
            this.copiarMobiliarioSeleccionado();
            this.enModificar = true;
        }
    }
    
    public void detallesMobiliario() {
        if (this.mobiliarioSeleccionado!=null) {
            this.tituloFormulario = "Detalles de Mobiliario";
            this.copiarMobiliarioSeleccionado();
            this.enDetalles = true;
        }
    }
    
    public void eliminarMobiliario() {
        if (this.mobiliarioSeleccionado != null) {
            try {
                this.copiarMobiliarioSeleccionado();
                this.mobiliarioServicio.eliminarMobiliario(this.mobiliario.getCodigo());
                this.listaMobiliario = this.mobiliarioServicio.obtenerTodasMobiliario();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mobiliario eliminado.", "Se ha eliminado el mobiliario " + this.mobiliario);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar el mobiliraio. ", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
    
    public void guardarMobiliario()
    {
        if (this.enNueva)
        {
            try {
                this.tipomobiliarioSeleccionado = new TipoMobiliario();
                this.tipomobiliarioSeleccionado.setCodigo(this.mobiliario.getCodTipoMobiliario());
                this.mobiliario.setTipoMobiliario(tipomobiliarioSeleccionado);
                this.mobiliarioServicio.crearMobiliario(mobiliario);
                this.enNueva = false;
                this.listaMobiliario= this.mobiliarioServicio.obtenerTodasMobiliario();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ficha Creada.", "Se ha creado la " + this.mobiliario);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear Ficha.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            try {
                
                this.tipomobiliarioSeleccionado = new TipoMobiliario();
                this.tipomobiliarioSeleccionado.setCodigo(this.mobiliario.getCodTipoMobiliario());
                this.mobiliario.setTipoMobiliario(tipomobiliarioSeleccionado);
                this.mobiliarioServicio.crearMobiliario(mobiliario);
                this.enModificar = false;
                this.listaMobiliario= this.mobiliarioServicio.obtenerTodasMobiliario();
                this.listaTipoMobiliario= this.tipomobiliarioServicio.obtenerTipoMobiliario();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mobiliario Actulizado.", "Se ha actualizado la " + this.mobiliario);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar el mobiliario.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            
                
            }
        
    }
       
    
    
    
    private void copiarMobiliarioSeleccionado() {
        this.mobiliario= new Mobiliario();
        this.mobiliario.setCodigo(this.mobiliarioSeleccionado.getCodigo());
        this.mobiliario.setTipoMobiliario(this.mobiliarioSeleccionado.getTipoMobiliario());
        this.mobiliario.setNombre(this.mobiliarioSeleccionado.getNombre());
        this.mobiliario.setExistencia(this.mobiliarioSeleccionado.getExistencia());
        this.mobiliario.setValor(this.mobiliarioSeleccionado.getValor());        
    }
    public void cancelar() {
        this.enNueva = false;
        this.enModificar = false;
        this.enDetalles = false;
    }

    public List<Mobiliario> getListaMobiliario() {
        return listaMobiliario;
    }

    public void setListaMobiliario(List<Mobiliario> listaMobiliario) {
        this.listaMobiliario = listaMobiliario;
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

    public List<TipoMobiliario> getListaTipoMobiliario() {
        return listaTipoMobiliario;
    }

    public void setListaTipoMobiliario(List<TipoMobiliario> listaTipoMobiliario) {
        this.listaTipoMobiliario = listaTipoMobiliario;
    }

    public TipoMobiliario getTipoMobiliario() {
        return tipoMobiliario;
    }

    public void setTipoMobiliario(TipoMobiliario tipoMobiliario) {
        this.tipoMobiliario = tipoMobiliario;
    }

    public TipoMobiliario getTipomobiliarioSeleccionado() {
        return tipomobiliarioSeleccionado;
    }

    public void setTipomobiliarioSeleccionado(TipoMobiliario tipomobiliarioSeleccionado) {
        this.tipomobiliarioSeleccionado = tipomobiliarioSeleccionado;
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

    public TipoMobiliarioServicio getTipomobiliarioServicio() {
        return tipomobiliarioServicio;
    }

    public void setTipomobiliarioServicio(TipoMobiliarioServicio tipomobiliarioServicio) {
        this.tipomobiliarioServicio = tipomobiliarioServicio;
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
    
    
    

