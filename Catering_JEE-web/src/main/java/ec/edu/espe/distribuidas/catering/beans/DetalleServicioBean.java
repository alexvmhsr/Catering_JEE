/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

import com.espe.distribuidas.catering.modelo.DetalleServicio;
import com.espe.distribuidas.catering.modelo.Paquete;
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

/**
 *
 * @author Vane
 */
@ManagedBean
@ViewScoped
public class DetalleServicioBean implements Serializable {
    
    private List <DetalleServicio> listaDetalleServicio;
    private DetalleServicio detalleServicio;
    private DetalleServicio detalleServicioSeleccionado;
    
    private List<Paquete> listaPaquete;
    private Integer codigoPaqueteSeleccionado;
    private Paquete paqueteSeleccionado;
    
    private List<Servicio> listaServicio;
    private Integer codigoServicioSeleccionado;
    private Servicio ServicioSeleccionado;
    
    private String tituloFormulario;

    private boolean enNueva;
    private boolean enModificar;
    private boolean enDetalles;
    
     @EJB
    private ServicioServicio servicioServicio;
     
     @PostConstruct
    public void postConstructor() {
        this.listaDetalleServicio = this.servicioServicio.ObtenerDetalleServicios();
        this.listaPaquete=this.servicioServicio.ObtenerPaquetes();
        this.listaServicio = this.servicioServicio.ObtenerServicios();
    }
    public void nuevoDetalleServicio() {
        this.detalleServicio = new DetalleServicio();
        this.enNueva = true;
        this.tituloFormulario = "Creación de Detalle";
    }

    public void modificarDetalleServicio() {
        if (this.detalleServicioSeleccionado != null) {
            this.tituloFormulario = "Modificación de Detalle";
            this.copiarDetalleFichaMascotaSeleccionada();
            this.enModificar = true;
        }
    }

    public void detallesDetalleServicio() {
        if (this.detalleServicioSeleccionado != null) {
            this.tituloFormulario = "Detalles de Servicio";
            this.copiarDetalleFichaMascotaSeleccionada();
            this.enDetalles = true;
        }
    }
    
  public void guardarDetalleFichaMascota() {
        if (this.enNueva) {
            try {
//                 
                this.paqueteSeleccionado=new Paquete();
                this.ServicioSeleccionado= new Servicio();
                this.paqueteSeleccionado.setCodigo(this.detalleServicio.getCodigoPaquete());
                this.ServicioSeleccionado.setCodigo(this.detalleServicio.getCodigoServicio());
                this.detalleServicio.setPaquete(paqueteSeleccionado);
                this.detalleServicio.setServicio(ServicioSeleccionado);
                this.servicioServicio.crearDetalleServicio(this.detalleServicio);
                this.enNueva = false;
                this.listaDetalleServicio = this.servicioServicio.ObtenerDetalleServicios();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Detalle Creado.", "Se ha creado el " + this.detalleServicio);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear Detalle.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            try {
                   this.paqueteSeleccionado=new Paquete();
                this.ServicioSeleccionado= new Servicio();
                this.paqueteSeleccionado.setCodigo(this.detalleServicio.getCodigoPaquete());
                this.ServicioSeleccionado.setCodigo(this.detalleServicio.getCodigoServicio());
                this.detalleServicio.setPaquete(paqueteSeleccionado);
                this.detalleServicio.setServicio(ServicioSeleccionado);
                this.servicioServicio.ActualizarServicioDetalle(this.detalleServicio);
                this.enModificar = false;
                this.listaDetalleServicio = this.servicioServicio.ObtenerDetalleServicios();
                this.listaPaquete=this.servicioServicio.ObtenerPaquetes();
                this.listaServicio = this.servicioServicio.ObtenerServicios();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Detalle Actualizado.", "Se ha actualizado el " + this.detalleServicio);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar detalle.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
  
      

  
  private void copiarDetalleFichaMascotaSeleccionada() {
        this.detalleServicio = new DetalleServicio();
        this.detalleServicio.setCodigoPaquete(this.detalleServicioSeleccionado.getCodigoPaquete());
        this.detalleServicio.setCodigoServicio(this.detalleServicioSeleccionado.getCodigoServicio());
        this.detalleServicio.setCantidad(this.detalleServicioSeleccionado.getCantidad());
        this.detalleServicio.setValorTotal(this.detalleServicioSeleccionado.getValorTotal());
        
    }
  
  public void cancelar() {
        this.enNueva = false;
        this.enModificar = false;
        this.enDetalles = false;
    }

    public List<DetalleServicio> getListaDetalleServicio() {
        return listaDetalleServicio;
    }

    public void setListaDetalleServicio(List<DetalleServicio> listaDetalleServicio) {
        this.listaDetalleServicio = listaDetalleServicio;
    }

    public DetalleServicio getDetalleServicio() {
        return detalleServicio;
    }

    public void setDetalleServicio(DetalleServicio detalleServicio) {
        this.detalleServicio = detalleServicio;
    }

    public DetalleServicio getDetalleServicioSeleccionado() {
        return detalleServicioSeleccionado;
    }

    public void setDetalleServicioSeleccionado(DetalleServicio detalleServicioSeleccionado) {
        this.detalleServicioSeleccionado = detalleServicioSeleccionado;
    }

    public List<Paquete> getListaPaquete() {
        return listaPaquete;
    }

    public void setListaPaquete(List<Paquete> listaPaquete) {
        this.listaPaquete = listaPaquete;
    }

    public Integer getCodigoPaqueteSeleccionado() {
        return codigoPaqueteSeleccionado;
    }

    public void setCodigoPaqueteSeleccionado(Integer codigoPaqueteSeleccionado) {
        this.codigoPaqueteSeleccionado = codigoPaqueteSeleccionado;
    }

    public Paquete getPaqueteSeleccionado() {
        return paqueteSeleccionado;
    }

    public void setPaqueteSeleccionado(Paquete paqueteSeleccionado) {
        this.paqueteSeleccionado = paqueteSeleccionado;
    }

    public List<Servicio> getListaServicio() {
        return listaServicio;
    }

    public void setListaServicio(List<Servicio> listaServicio) {
        this.listaServicio = listaServicio;
    }

    public Integer getCodigoServicioSeleccionado() {
        return codigoServicioSeleccionado;
    }

    public void setCodigoServicioSeleccionado(Integer codigoServicioSeleccionado) {
        this.codigoServicioSeleccionado = codigoServicioSeleccionado;
    }

    public Servicio getServicioSeleccionado() {
        return ServicioSeleccionado;
    }

    public void setServicioSeleccionado(Servicio ServicioSeleccionado) {
        this.ServicioSeleccionado = ServicioSeleccionado;
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

    public ServicioServicio getServicioServicio() {
        return servicioServicio;
    }

    public void setServicioServicio(ServicioServicio servicioServicio) {
        this.servicioServicio = servicioServicio;
    }
    
  
}
