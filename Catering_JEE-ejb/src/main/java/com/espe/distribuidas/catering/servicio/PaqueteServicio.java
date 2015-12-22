/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.servicio;

import com.espe.distribuidas.catering.dao.PaqueteDAO;
import com.espe.distribuidas.catering.modelo.Paquete;
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
public class PaqueteServicio {
    
    @EJB
    private PaqueteDAO paqueteDAO= new PaqueteDAO();
    
    public  List<Paquete> ObtenerTodas()
    {
        return this.paqueteDAO.findAll();
    }
    
     public boolean validar(String nombre, Integer activo, BigDecimal precio, Integer Items) {
        Paquete paqueteTmp = new Paquete();
        paqueteTmp.setNombre(nombre);
        paqueteTmp.setActivo(activo);
        paqueteTmp.setPrecio(precio);
        paqueteTmp.setItems(Items);
        if(paqueteDAO.find(paqueteTmp, false).isEmpty()){
            return false;
        }else return true;
    }
     
    public void ActualizarPaquete(Paquete paquete) {
        this.paqueteDAO.update(paquete);
    }
    
    
    
}