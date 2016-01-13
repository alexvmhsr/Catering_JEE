/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

import com.espe.distribuidas.catering.servicio.UsuarioServicio;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author PabloA
 */
@ManagedBean(name="loginBean")
@ViewScoped
public class loginBean implements Serializable{
    

    private String nombre;
    private String clave;
    private boolean logeado = false;
    private final HttpServletRequest httpServletRequest;
    private final FacesContext faceContext;
    
    public boolean estaLogeado() {
        return logeado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    @EJB
    private UsuarioServicio usuarioServicio;
   // Usuario usuario = null;
    //UsuarioFacade uf = new UsuarioFacade();

    public loginBean() {
       
        faceContext=FacesContext.getCurrentInstance();
        httpServletRequest=(HttpServletRequest)faceContext.getExternalContext().getRequest();
        if(httpServletRequest.getSession().getAttribute("sessionUsuario")!=null)
        {
            nombre=httpServletRequest.getSession().getAttribute("sessionUsuario").toString();
        }
    }
    
    

   public void login(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg;
        boolean valid = usuarioServicio.validar(nombre, clave);
            if (valid) {
                logeado = true;
                msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenid@ ", nombre);
            } else {
                logeado = false;
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error",
                        "Credenciales no validas");
                context.addCallbackParam("view", "/faces/index.xhtml");
            }
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.addCallbackParam("estaLogeado", logeado);
            if (logeado) {
                context.addCallbackParam("view", "faces/Principal.xhtml");
            }
        
            
    }
    public void cerrarSession()
    {
        ExternalContext ctx
                = FacesContext.getCurrentInstance().getExternalContext();
        String ctxPath
                = ((ServletContext) ctx.getContext()).getContextPath();

        try {
    // Usar el contexto de JSF para invalidar la sesiÃ³n,
            // NO EL DE SERVLETS (nada de HttpServletRequest)
            ((HttpSession) ctx.getSession(false)).invalidate();

    // RedirecciÃ³n de nuevo con el contexto de JSF,
            // si se usa una HttpServletResponse fallarÃ¡.
            // Sin embargo, como ya estÃ¡ fuera del ciclo de vida 
            // de JSF se debe usar la ruta completa -_-U
            ctx.redirect(ctxPath + "/faces/index.xhtml");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }
 
 
}
         
        
    
