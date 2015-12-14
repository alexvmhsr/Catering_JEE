/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.catering.beans;

//import dao.UsuarioDao;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

//import model.Usuario;


/**
 *
 * @author PabloA
 */
@ManagedBean (name="Usuario")
@SessionScoped
public class DataExporterView implements Serializable{

    
    
    /**
     * Creates a new instance of DataExporterView
     
    public DataExporterView() {
    }
    private List<Usuario> usu;

    public List<Usuario> getUsu() {
        UsuarioDao usudao = new UsuarioDao();
        
        return usudao.verUsuarios();
    }

    public void setUsu(List<Usuario> usu) {
        this.usu = usu;
    }
    */     
   
}
