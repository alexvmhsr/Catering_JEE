/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

import com.espe.distribuidas.catering.modelo.Usuario;
import com.espe.distribuidas.catering.servicio.UsuarioServicio;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.codec.binary.Base64;
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
        this.usuario.setContrasenia(Encriptar(usuario.getContrasenia()));
        this.enNueva = true;
        this.tituloFormulario = "Creación de Usuario";
    }

    public void modificarUsuario() {
        if (this.usuarioSeleccionado != null) {
            this.tituloFormulario = "Modificación de Usuario";
            this.copiarUsuarioSeleccionado();
            this.enModificar = true;
        }
    }

    public void detallesUsuario() {
        if (this.usuarioSeleccionado != null) {
            this.tituloFormulario = "Detalles de Usuario";
            this.copiarUsuarioSeleccionado();
            this.enDetalles = true;
        }
    }

    public static String Encriptar(String texto) {

        String secretKey = "qualityinfosolutions"; //llave para encriptar datos
        String base64EncryptedString = "";

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
            byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

            SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] plainTextBytes = texto.getBytes("utf-8");
            byte[] buf = cipher.doFinal(plainTextBytes);
            byte[] base64Bytes = Base64.encodeBase64(buf);
            base64EncryptedString = new String(base64Bytes);

        } catch (Exception ex) {
        }
        return base64EncryptedString;
    }

    public void guardarUsuario() {
        if (this.enNueva) {
            try {
                this.usuarioServicio.crearUsuario(this.usuario);
                this.enNueva = false;
                this.usuarios = this.usuarioServicio.obtenerUsuarios();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Creado.", "Se ha creado el " + this.usuario);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear Usuario.", e.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            try {
                this.usuarioServicio.actualizarUsuario(this.usuario);
                this.enModificar = false;
                this.usuarios = this.usuarioServicio.obtenerUsuarios();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Actualizado.", "Se ha actualizado el " + this.usuario);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al actualizar Usuario.", e.getMessage());
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
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario Eliminado.", "Se ha eliminado el Usuario " + this.usuario);
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al eliminar Usuario. ", e.getMessage());
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
