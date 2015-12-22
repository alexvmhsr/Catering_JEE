/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.servicio;

import com.espe.distribuidas.catering.dao.TipoMobiliarioDAO;
import com.espe.distribuidas.catering.exception.ValidacionException;
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
    
    
    
    public void crearTipoMobiliario(TipoMobiliario tipoMobiliario) {
        
        TipoMobiliario tipoMobiliarioTemp = new TipoMobiliario();
        tipoMobiliarioTemp.setCodigo(tipoMobiliario.getCodigo());
        List<TipoMobiliario> tipoMobiliarios = this.tipoMobiliarioDAO.find(tipoMobiliarioTemp);
        if (tipoMobiliarios == null || tipoMobiliarios.isEmpty()) {
            this.tipoMobiliarioDAO.insert(tipoMobiliario);
        } else {
            throw new ValidacionException("El tipo de evento : " + tipoMobiliario.getCodigo()+ " ya existe.");
        }
    }
    
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
     
      public void eliminarTipoMobiliario(Integer codigo ) {
        TipoMobiliario tipoMobiliario = this.tipoMobiliarioDAO.findById(codigo, false);
        this.tipoMobiliarioDAO.remove(tipoMobiliario);
    }
    
}
