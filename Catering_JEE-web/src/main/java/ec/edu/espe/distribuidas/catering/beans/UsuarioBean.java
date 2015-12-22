/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

import com.espe.distribuidas.catering.modelo.Usuario;
import com.espe.distribuidas.catering.servicio.UsuarioServicio;
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
public class UsuarioBean implements Serializable {

    /**
     * Creates a new instance of UsuarioBean
     */
    @EJB
    private UsuarioServicio usuarioServicio;

    private List<Usuario> usuarios;

    private Usuario usuario;

    private Usuario usuarioSeleccionado;

    private String tituloFormulario;

    private boolean nuevo;

    private boolean enNueva;
    private boolean enModificar;
    private boolean enDetalles;

    @PostConstruct
    public void init() {
        this.usuarios = this.usuarioServicio.obtenerUsuarios();
    }

    public UsuarioServicio getUsuarioServicio() {
        return usuarioServicio;
    }

    public void setUsuarioServicio(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
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

    public void nuevoUsuario() {
        this.usuario = new Usuario();
        this.enNueva = true;
        this.tituloFormulario = "Creación de Cliente";
    }

    public void modificarUsuario() {
        if (this.usuarioSeleccionado != null) {
            this.tituloFormulario = "Modificación de Cliente";
            this.copiarUsuarioSeleccionado();
            this.enModificar = true;
        }
    }

    public void detallesUsuario() {
        if (this.usuarioSeleccionado != null) {
            this.tituloFormulario = "Detalles de Cliente";
            this.copiarUsuarioSeleccionado();
            this.enDetalles = true;
        }
    }

    public void guardarUsuario() {
        if (this.enNueva) {
            try {
                this.usuarioServicio.crearUsuario(this.usuario);
                this.enNueva = false;
                this.usuarios = this.usuarioServicio.obtenerUsuarios();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Creado.", "Se ha creado el " + this.usuario);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cliente.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            try {
                this.usuarioServicio.actualizarUsuario(this.usuario);
                this.enModificar = false;
                this.usuarios = this.usuarioServicio.obtenerUsuarios();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Actualizado.", "Se ha actualizado el " + this.usuario);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar cliente.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public void eliminarUsuario() {
        if (this.usuarioSeleccionado != null) {
            try {
                this.copiarUsuarioSeleccionado();
                this.usuarioServicio.eliminarUsuario(this.usuario.getIdUsuario());
                this.usuarios = this.usuarioServicio.obtenerUsuarios();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Eliminado.", "Se ha eliminado el cliente " + this.usuario);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar cliente. ", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        }
    }

    private void copiarUsuarioSeleccionado() {
        this.usuario = new Usuario();
        this.usuario.setIdUsuario(this.usuarioSeleccionado.getIdUsuario());
        this.usuario.setNombre(this.usuarioSeleccionado.getNombre());
        this.usuario.setContrasenia(this.usuarioSeleccionado.getContrasenia());
        this.usuario.setCorreo(this.usuarioSeleccionado.getCorreo());
        this.usuario.setEstado(this.usuarioSeleccionado.getEstado());
    }

    public void cancelar() {
        this.enNueva = false;
        this.enModificar = false;
        this.enDetalles = false;
    }

    public void selectUsuarioFromDialog(Usuario usuario) {
        RequestContext.getCurrentInstance().closeDialog(usuario);
    }
}
