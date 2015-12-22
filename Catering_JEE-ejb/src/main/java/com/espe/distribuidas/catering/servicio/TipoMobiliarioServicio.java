/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.servicio;

import com.espe.distribuidas.catering.dao.TipoMobiliarioDAO;
import com.espe.distribuidas.catering.modelo.TipoMobiliario;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Vane
 */
@LocalBean
@Stateless
public class TipoMobiliarioServicio {
    @EJB
    private TipoMobiliarioDAO tipoMobiliarioDAO;
    
     public List<TipoMobiliario> ObtenerTodas() {
        return this.tipoMobiliarioDAO.findAll();
    }
     
     public boolean validar(String nombre, String descripcion) {
        TipoMobiliario tipMovTmp = new TipoMobiliario();
        tipMovTmp.setNombre(nombre);
        tipMovTmp.setDescripcion(descripcion);
        if(tipoMobiliarioDAO.find(tipMovTmp, false).isEmpty()){
            return false;
        }else return true;
    }
     public void ActualizarTipoMobiliario(TipoMobiliario tipMov) {
        this.tipoMobiliarioDAO.update(tipMov);
    }
    
}
