/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

import com.espe.distribuidas.catering.modelo.Alimentacion;
import com.espe.distribuidas.catering.modelo.DetalleAlimentacion;
import com.espe.distribuidas.catering.modelo.Paquete;
import com.espe.distribuidas.catering.servicio.AlimentacionServicio;
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
public class DetalleAlimentacionBean implements Serializable {
    
    private List <DetalleAlimentacion> listaDetalleAlimentacion;
    private DetalleAlimentacion detalleAlimentacion;
    private DetalleAlimentacion detalleAlimentacionSeleccionado;
    
    private List<Paquete> listaPaquete;
    private Integer codigoPaqueteSeleccionado;
    private Paquete paqueteSeleccionado;
    
    private List<Alimentacion> listaAlimentacion;
    private Integer codigoAlimentacionSeleccionado;
    private Alimentacion AlimentacionSeleccionado;
    
    private String tituloFormulario;

    private boolean enNueva;
    private boolean enModificar;
    private boolean enDetalles;
    
    @EJB
    private AlimentacionServicio alimentacionServicio;
     
    @PostConstruct
    public void postConstructor() {
        this.listaDetalleAlimentacion = this.alimentacionServicio.ObtenerDetalleAlimentacion();
        this.listaPaquete=this.alimentacionServicio.ObtenerPaquetes();
        this.listaAlimentacion = this.alimentacionServicio.ObtenerTodas();
    }
    public void nuevoDetalleServicio() {
        this.detalleAlimentacion = new DetalleAlimentacion();
        this.enNueva = true;
        this.tituloFormulario = "Creación de Detalle";
    }

    public void modificarDetalleServicio() {
        if (this.detalleAlimentacionSeleccionado != null) {
            this.tituloFormulario = "Modificación de Detalle";
            this.copiarDetalleFichaMascotaSeleccionada();
            this.enModificar = true;
        }
    }

    public void detallesDetalleServicio() {
        if (this.detalleAlimentacionSeleccionado != null) {
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
                this.AlimentacionSeleccionado= new Alimentacion();
                this.paqueteSeleccionado.setCodigo(this.detalleAlimentacion.getCodigoPaquete());
                this.AlimentacionSeleccionado.setCodigo(this.detalleAlimentacion.getCodigoAlimentacion());
                this.detalleAlimentacion.setPaquete(paqueteSeleccionado);
                this.detalleAlimentacion.setAlimentacion(AlimentacionSeleccionado);
                this.alimentacionServicio.crearDetalleAlimentacion(this.detalleAlimentacion);
                this.enNueva = false;
                this.listaDetalleAlimentacion = this.alimentacionServicio.ObtenerDetalleAlimentacion();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Detalle Creado.", "Se ha creado el " + this.detalleAlimentacion);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear Detalle.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            try {
                   this.paqueteSeleccionado=new Paquete();
                this.AlimentacionSeleccionado= new Alimentacion();
                this.paqueteSeleccionado.setCodigo(this.detalleAlimentacion.getCodigoPaquete());
                this.AlimentacionSeleccionado.setCodigo(this.detalleAlimentacion.getCodigoAlimentacion());
                this.detalleAlimentacion.setPaquete(paqueteSeleccionado);
                this.detalleAlimentacion.setAlimentacion(AlimentacionSeleccionado);
                this.alimentacionServicio.ActualizarAlimentacionDetalle(this.detalleAlimentacion);
                this.enModificar = false;
                this.listaDetalleAlimentacion = this.alimentacionServicio.ObtenerDetalleAlimentacion();
                this.listaPaquete=this.alimentacionServicio.ObtenerPaquetes();
                this.listaAlimentacion = this.alimentacionServicio.ObtenerTodas();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Detalle Actualizado.", "Se ha actualizado el " + this.detalleAlimentacion);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar detalle.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
  
      

  
  private void copiarDetalleFichaMascotaSeleccionada() {
        this.detalleAlimentacion = new DetalleAlimentacion();
        this.detalleAlimentacion.setCodigoPaquete(this.detalleAlimentacionSeleccionado.getCodigoPaquete());
        this.detalleAlimentacion.setCodigoAlimentacion(this.detalleAlimentacionSeleccionado.getCodigoAlimentacion());
        this.detalleAlimentacion.setCantidad(this.detalleAlimentacionSeleccionado.getCantidad());
        this.detalleAlimentacion.setValorTotal(this.detalleAlimentacionSeleccionado.getValorTotal());
        
    }
  
  public void cancelar() {
        this.enNueva = false;
        this.enModificar = false;
        this.enDetalles = false;
    }

    public List<DetalleAlimentacion> getListaDetalleAlimentacion() {
        return listaDetalleAlimentacion;
    }

    public void setListaDetalleAlimentacion(List<DetalleAlimentacion> listaDetalleAlimentacion) {
        this.listaDetalleAlimentacion = listaDetalleAlimentacion;
    }

    public DetalleAlimentacion getDetalleAlimentacion() {
        return detalleAlimentacion;
    }

    public void setDetalleAlimentacion(DetalleAlimentacion detalleAlimentacion) {
        this.detalleAlimentacion = detalleAlimentacion;
    }

    public DetalleAlimentacion getDetalleAlimentacionSeleccionado() {
        return detalleAlimentacionSeleccionado;
    }

    public void setDetalleAlimentacionSeleccionado(DetalleAlimentacion detalleAlimentacionSeleccionado) {
        this.detalleAlimentacionSeleccionado = detalleAlimentacionSeleccionado;
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

    public List<Alimentacion> getListaAlimentacion() {
        return listaAlimentacion;
    }

    public void setListaAlimentacion(List<Alimentacion> listaAlimentacion) {
        this.listaAlimentacion = listaAlimentacion;
    }

    public Integer getCodigoAlimentacionSeleccionado() {
        return codigoAlimentacionSeleccionado;
    }

    public void setCodigoAlimentacionSeleccionado(Integer codigoAlimentacionSeleccionado) {
        this.codigoAlimentacionSeleccionado = codigoAlimentacionSeleccionado;
    }

    public Alimentacion getAlimentacionSeleccionado() {
        return AlimentacionSeleccionado;
    }

    public void setAlimentacionSeleccionado(Alimentacion AlimentacionSeleccionado) {
        this.AlimentacionSeleccionado = AlimentacionSeleccionado;
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

    public AlimentacionServicio getAlimentacionServicio() {
        return alimentacionServicio;
    }

    public void setAlimentacionServicio(AlimentacionServicio alimentacionServicio) {
        this.alimentacionServicio = alimentacionServicio;
    }
  
  
}
