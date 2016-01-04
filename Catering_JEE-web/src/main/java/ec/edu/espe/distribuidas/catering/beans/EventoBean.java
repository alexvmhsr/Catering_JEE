/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

import com.espe.distribuidas.catering.modelo.Cliente;
import com.espe.distribuidas.catering.modelo.Evento;
import com.espe.distribuidas.catering.modelo.Paquete;
import com.espe.distribuidas.catering.modelo.TipoEvento;
import com.espe.distribuidas.catering.servicio.ClienteService;
import com.espe.distribuidas.catering.servicio.EventoServicio;
import com.espe.distribuidas.catering.servicio.PaqueteServicio;
import com.espe.distribuidas.catering.servicio.TipoEventoServicio;
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
public class EventoBean implements Serializable {
    
     
     
     private List<Evento> ListaEvento;
     private Evento evento;
     private Evento eventoSeleccionado;
         
     private List<TipoEvento> ListaTipoEvento;
     private TipoEvento tipoEvento;
     private TipoEvento tipoEventoSeleccionado;
     
     private List<Paquete> ListaPaquete;
     private  Integer codigopaqueteSeleccionado;
     private Paquete paqueteSeleccionado;
     
     private List<Cliente> ListaCliente;
     private String codigoclienteSeleccionado;
     private Cliente clienteSeleccionado;
     
     @EJB
     private EventoServicio eventoServicio;
     
     @EJB
     private TipoEventoServicio tipoEventoServicio;
     
     @EJB 
     private PaqueteServicio paqueteservicio;
     
     @EJB
     private ClienteService clienteServicio;
     
     

    private String tituloFormulario;
     
     private boolean nuevo;
     private boolean enNueva;
     private boolean enModificar;
     private boolean enDetalles;
     
     @PostConstruct
      public void postConstructor() {
            this.ListaEvento = this.eventoServicio.obtenerEventos();
            this.ListaTipoEvento= this.tipoEventoServicio.ObtenerTodas();
            this.ListaPaquete= this.paqueteservicio.obtenerTodas();
            this.ListaCliente= this.clienteServicio.obtenerClientes();
            
      }
    
    public void nuevoEvento() {
        this.evento= new Evento();
        this.enNueva = true;
        this.tituloFormulario = "Creación de evento";
    }

    
    public void modificarEvento() {
        if (this.eventoSeleccionado != null) {
            this.tituloFormulario = "Modificación de evento";
            this.copiarEventoSeleccionado();
            this.enModificar = true;
        }
    }

    public void detallesEvento() {
        if (this.eventoSeleccionado != null) {
            this.tituloFormulario = "Detalles de Evento";
            this.copiarEventoSeleccionado();
            this.enDetalles = true;
        }
    }
    public void eliminarEvento() {
        if (this.eventoSeleccionado!= null) {
            try {
                this.copiarEventoSeleccionado();
                this.eventoServicio.eliminarEvento(this.evento.getCodigo());
                this.ListaEvento= this.eventoServicio.obtenerEventos();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento Eliminado.", "Se ha eliminado el evento " + this.evento);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar evento. ", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        }
    }

    public void guardarEvento() {
        if (this.enNueva) {
            try {
                this.tipoEventoSeleccionado = new TipoEvento();
                this.paqueteSeleccionado = new Paquete();
                this.clienteSeleccionado = new Cliente();
                this.tipoEventoSeleccionado.setCodigo(this.evento.getCodigoTipoEvento());
                this.paqueteSeleccionado.setCodigo(this.evento.getCodigoPaquete());
                this.clienteSeleccionado.setCodigo(this.evento.getCodigoCliente());
                this.evento.setTipoEvento(tipoEventoSeleccionado);
                this.evento.setPaquete(paqueteSeleccionado);
                this.evento.setCliente(clienteSeleccionado);
                this.eventoServicio.crearEvento(this.evento);
                this.enNueva = false;
                this.ListaEvento= this.eventoServicio.obtenerEventos();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento Creado.", "Se ha creado la " + this.evento);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear el evento.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            try {
                this.tipoEventoSeleccionado = new TipoEvento();
                this.paqueteSeleccionado = new Paquete();
                this.clienteSeleccionado = new Cliente();
                this.tipoEventoSeleccionado.setCodigo(this.evento.getCodigoTipoEvento());
                this.paqueteSeleccionado.setCodigo(this.evento.getCodigoPaquete());
                this.clienteSeleccionado.setCodigo(this.evento.getCodigoCliente());
                this.evento.setTipoEvento(tipoEventoSeleccionado);
                this.evento.setPaquete(paqueteSeleccionado);
                this.evento.setCliente(clienteSeleccionado);
                this.eventoServicio.actualizarEvento(this.evento);
                this.enModificar = false;
                this.ListaEvento = this.eventoServicio.obtenerEventos();
                this.ListaTipoEvento = this.tipoEventoServicio.ObtenerTodas();
                this.ListaPaquete = this.paqueteservicio.obtenerTodas();
                this.ListaCliente = this.clienteServicio.obtenerClientes();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Evento Actualizada.", "Se ha actualizado la " + this.evento);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar el evento.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
    

    private void copiarEventoSeleccionado() {
        
        this.evento = new Evento();
        this.evento.setCodigo(this.eventoSeleccionado.getCodigo());
        this.evento.setCodigoTipoEvento(this.eventoSeleccionado.getCodigoTipoEvento());
        this.evento.setCodigoPaquete(this.eventoSeleccionado.getCodigoPaquete());
        this.evento.setCodigoCliente(this.eventoSeleccionado.getCodigoCliente());
        this.evento.setFechaEventoInicio(this.eventoSeleccionado.getFechaEventoInicio());
        this.evento.setFechaEventoFinal(this.eventoSeleccionado.getFechaEventoFinal());
        this.evento.setAsistentes(this.eventoSeleccionado.getAsistentes());
        this.evento.setDireccion(this.eventoSeleccionado.getDireccion());
        this.evento.setTelefono(this.eventoSeleccionado.getTelefono());

    }

    public void cancelar() {
        this.enNueva = false;
        this.enModificar = false;
        this.enDetalles = false;
    }

    public List<Evento> getListaEvento() {
        return ListaEvento;
    }

    public void setListaEvento(List<Evento> ListaEvento) {
        this.ListaEvento = ListaEvento;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public Evento getEventoSeleccionado() {
        return eventoSeleccionado;
    }

    public void setEventoSeleccionado(Evento eventoSeleccionado) {
        this.eventoSeleccionado = eventoSeleccionado;
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

    public List<Paquete> getListaPaquete() {
        return ListaPaquete;
    }

    public void setListaPaquete(List<Paquete> ListaPaquete) {
        this.ListaPaquete = ListaPaquete;
    }

    public Integer getCodigopaqueteSeleccionado() {
        return codigopaqueteSeleccionado;
    }

    public void setCodigopaqueteSeleccionado(Integer codigopaqueteSeleccionado) {
        this.codigopaqueteSeleccionado = codigopaqueteSeleccionado;
    }

    public Paquete getPaqueteSeleccionado() {
        return paqueteSeleccionado;
    }

    public void setPaqueteSeleccionado(Paquete paqueteSeleccionado) {
        this.paqueteSeleccionado = paqueteSeleccionado;
    }

    public List<Cliente> getListaCliente() {
        return ListaCliente;
    }

    public void setListaCliente(List<Cliente> ListaCliente) {
        this.ListaCliente = ListaCliente;
    }

    public String getCodigoclienteSeleccionado() {
        return codigoclienteSeleccionado;
    }

    public void setCodigoclienteSeleccionado(String codigoclienteSeleccionado) {
        this.codigoclienteSeleccionado = codigoclienteSeleccionado;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
    }

    public EventoServicio getEventoServicio() {
        return eventoServicio;
    }

    public void setEventoServicio(EventoServicio eventoServicio) {
        this.eventoServicio = eventoServicio;
    }

    public TipoEventoServicio getTipoEventoServicio() {
        return tipoEventoServicio;
    }

    public void setTipoEventoServicio(TipoEventoServicio tipoEventoServicio) {
        this.tipoEventoServicio = tipoEventoServicio;
    }

    public PaqueteServicio getPaqueteservicio() {
        return paqueteservicio;
    }

    public void setPaqueteservicio(PaqueteServicio paqueteservicio) {
        this.paqueteservicio = paqueteservicio;
    }

    public ClienteService getClienteServicio() {
        return clienteServicio;
    }

    public void setClienteServicio(ClienteService clienteServicio) {
        this.clienteServicio = clienteServicio;
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
