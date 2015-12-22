/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.servicio;

import com.espe.distribuidas.catering.dao.ServicioDAO;
import com.espe.distribuidas.catering.exception.ValidacionException;
import com.espe.distribuidas.catering.modelo.Servicio;
import java.math.BigDecimal;
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
public class ServicioServicio {
    
    @EJB
    private ServicioDAO servicioDAO;
    
    public void crearServicio(Servicio servicio) {
            this.servicioDAO.insert(servicio);
    }
    
    public List<Servicio> ObtenerTodas() {
        return this.servicioDAO.findAll();
    }
    
    public boolean validar(String tipoServicio, BigDecimal valor) {
        Servicio serTmp = new Servicio();
        serTmp.setTipoServicio(tipoServicio);
        serTmp.setValor(valor);
        if(servicioDAO.find(serTmp, false).isEmpty()){
            return false;
        }else return true;
    }
    
    public void ActualizarServicio(Servicio ser) {
        this.servicioDAO.update(ser);
    }
    
    public void eliminarServicio(Integer codigo) {
        Servicio servicio = this.servicioDAO.findById(codigo, false);
        this.servicioDAO.remove(servicio);
    }
}
