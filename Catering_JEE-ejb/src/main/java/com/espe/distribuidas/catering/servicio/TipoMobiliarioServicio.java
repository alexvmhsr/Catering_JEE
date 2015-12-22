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

    

    public void actualizarTipoMobiliario(TipoMobiliario tipMov1) {
        this.tipoMobiliarioDAO.update(tipMov1);
    }

    public void crearTipoMobiliario(TipoMobiliario tipMov2) {
        this.tipoMobiliarioDAO.insert(tipMov2);
    }

    public List<TipoMobiliario> obtenerTipoMobiliario() {
        return this.tipoMobiliarioDAO.findAll();
    }

    public void eliminarTipoMobiliario(Integer codigo1) {
        TipoMobiliario tipMov3 = this.tipoMobiliarioDAO.findById(codigo1, false);
        this.tipoMobiliarioDAO.remove(tipMov3);
    }
}
