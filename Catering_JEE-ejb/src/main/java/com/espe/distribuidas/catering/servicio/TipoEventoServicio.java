/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.servicio;

import com.espe.distribuidas.catering.dao.TipoEventoDAO;
import com.espe.distribuidas.catering.exception.ValidacionException;
import com.espe.distribuidas.catering.modelo.TipoEvento;
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
public class TipoEventoServicio {
    
    @EJB
    private TipoEventoDAO tipoEventoDAO;
    
    public void crearTipoEveto(TipoEvento tipoEvento) {

            this.tipoEventoDAO.insert(tipoEvento);
     
    }
    
    public List<TipoEvento> obtenerTipoEventos() {
        return this.tipoEventoDAO.findAll();
    }
    
    public boolean validar(String nombre, String detalle) {
        TipoEvento usuTmp = new TipoEvento();
        usuTmp.setNombre(nombre);
        usuTmp.setDetalle(detalle);
        if(tipoEventoDAO.find(usuTmp, false).isEmpty()){
            return false;
        }else return true;
    }
    
    public void ActualizarTipoEvento( TipoEvento tipeven) {
        this.tipoEventoDAO.update(tipeven);
    }
    public void eliminarTipoEvento(Integer codigo ) {
        TipoEvento tipoEvento = this.tipoEventoDAO.findById(codigo, false);
        this.tipoEventoDAO.remove(tipoEvento);
    }
}
