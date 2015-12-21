/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;


import com.espe.distribuidas.catering.modelo.Cliente;
import com.espe.distribuidas.catering.servicio.ClienteService;
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
 * @author macintosh
 */
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable{
    
    private List<Cliente> clientes;
    
    private Cliente cliente;
    
    private Cliente clienteSeleccionado;
    
    private String tituloFormulario;
    
    private boolean nuevo;
    
    private boolean enNueva;
    private boolean enModificar;
    private boolean enDetalles;
    
    @EJB
    private ClienteService clienteServicio;
    
    @PostConstruct
    public void postConstructor() {
        this.clientes = this.clienteServicio.obtenerClientes();
    }

    public void nuevoCliente() {
        this.cliente = new Cliente();
        this.enNueva =true;
        this.tituloFormulario = "Creación de Cliente";
    }
    
    public void modificarCliente() {
        if (this.clienteSeleccionado!=null) {
            this.tituloFormulario = "Modificación de Cliente";
            this.copiarClienteSeleccionado();
            this.enModificar = true;
        }
    }
    
    
    public void detallesCliente() {
        if (this.clienteSeleccionado!=null) {
            this.tituloFormulario = "Detalles de Cliente";
            this.copiarClienteSeleccionado();
            this.enDetalles = true;
        }
    }
    
    public void guardarCliente() {
        if (this.enNueva) {
            try {
                this.clienteServicio.crearCliente(this.cliente);
                this.enNueva = false;
                this.clientes = this.clienteServicio.obtenerClientes();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Creado.", "Se ha creado el "+this.cliente);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cliente.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            try {
                this.clienteServicio.actualizarCliente(this.cliente);
                this.enModificar = false;
                this.clientes = this.clienteServicio.obtenerClientes();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Actualizado.", "Se ha actualizado el "+this.cliente);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar cliente.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }
    
    public void eliminarCliente() {
        if (this.clienteSeleccionado != null) {
            try {
                this.copiarClienteSeleccionado();
                this.clienteServicio.eliminarCliente(this.cliente.getCodigo());
                this.clientes = this.clienteServicio.obtenerClientes();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Eliminado.", "Se ha eliminado el cliente " + this.cliente);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar cliente. ", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        }
    }
    
     private void copiarClienteSeleccionado() {
        this.cliente = new Cliente();
        this.cliente.setCodigo(this.clienteSeleccionado.getCodigo());
        this.cliente.setNombre(this.clienteSeleccionado.getNombre());
        this.cliente.setApellido(this.clienteSeleccionado.getApellido());
        this.cliente.setDireccion(this.clienteSeleccionado.getDireccion());
        this.cliente.setTelefono(this.clienteSeleccionado.getTelefono());
        this.cliente.setCorreo(this.clienteSeleccionado.getCodigo());
        
    }
     
    public void cancelar() {
        this.enNueva=false;
        this.enModificar = false;
        this.enDetalles = false;
    }
    
    public void selectClienteFromDialog(Cliente cliente) {
        RequestContext.getCurrentInstance().closeDialog(cliente);
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
    
    

   

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    

    public boolean isEnNueva() {
        return enNueva;
    }

    public Cliente getClienteSeleccionado() {
        return clienteSeleccionado;
    }

    public void setClienteSeleccionado(Cliente clienteSeleccionado) {
        this.clienteSeleccionado = clienteSeleccionado;
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
