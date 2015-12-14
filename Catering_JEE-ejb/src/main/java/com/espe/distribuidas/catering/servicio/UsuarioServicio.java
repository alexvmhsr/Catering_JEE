/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.servicio;

import com.espe.distribuidas.catering.dao.UsuarioDAO;
import com.espe.distribuidas.catering.modelo.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author PabloA
 */
@LocalBean
@Stateless
public class UsuarioServicio implements Serializable{

    @EJB
    private UsuarioDAO usuarioDAO;

    public List<Usuario> ObtenerTodas() {
        return this.usuarioDAO.findAll();
    }

    public boolean validar(String nombre, String clave) {
        Usuario usuTmp = new Usuario();
        usuTmp.setNombre(nombre);
        usuTmp.setContrasenia(clave);
        if(usuarioDAO.find(usuTmp, false).isEmpty()){
            return false;
        }else return true;
    }

   

    public void ActualizarSede(Usuario usu) {
        this.usuarioDAO.update(usu);
    }

    
    
}
