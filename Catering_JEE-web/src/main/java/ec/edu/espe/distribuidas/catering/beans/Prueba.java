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
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author PabloA
 */
@ManagedBean
@ViewScoped
public class Prueba implements Serializable{

    /**
     * Creates a new instance of Prueba
     */
    @EJB
    private UsuarioServicio usuarioServicio;
        
    private List<Usuario> listaUsuario;
    

    @PostConstruct
    public void init() {
        listaUsuario = usuarioServicio.ObtenerTodas();
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    
    
}
