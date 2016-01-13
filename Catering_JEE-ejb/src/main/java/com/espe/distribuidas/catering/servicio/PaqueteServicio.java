/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.servicio;

import com.espe.distribuidas.catering.dao.DetalleMobiliarioDAO;
import com.espe.distribuidas.catering.dao.PaqueteDAO;
import com.espe.distribuidas.catering.exception.ValidacionException;
import com.espe.distribuidas.catering.modelo.DetalleMobiliario;
import com.espe.distribuidas.catering.modelo.DetalleMobiliarioPK;
import com.espe.distribuidas.catering.modelo.Mobiliario;
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
    private PaqueteDAO paqueteDAO;
    
    @EJB
    DetalleMobiliarioDAO detallemobiliarioDAO;
    
    public void crearPaquete (Paquete paquete) {
            this.paqueteDAO.insert(paquete);
    }
    
    public  List<Paquete> obtenerTodas()
    {
        return this.paqueteDAO.findAll();
    }
    
     public List<DetalleMobiliario> obtenerTodasDetalleMobiliario() {
        return this.detallemobiliarioDAO.findAll();
    }
     
     public boolean validar(String nombre, String activo, BigDecimal precio, Integer Items) {
        Paquete paqueteTmp = new Paquete();
        paqueteTmp.setNombre(nombre);
        paqueteTmp.setActivo(activo);
        paqueteTmp.setPrecio(precio);
        paqueteTmp.setItems(Items);
        if(paqueteDAO.find(paqueteTmp, false).isEmpty()){
            return false;
        }else return true;
    }
     
    public void actualizarPaquete(Paquete paquete) {
        this.paqueteDAO.update(paquete);
    }
    
    public void eliminarDetalleMobiliario(DetalleMobiliarioPK pk) {
        DetalleMobiliario mobiliario = this.detallemobiliarioDAO.findById(pk, true);
        this.detallemobiliarioDAO.remove(mobiliario);
    }
    public void eliminarPaquete(Integer codigo) {
        Paquete paquete = this.paqueteDAO.findById(codigo, false);
        this.paqueteDAO.remove(paquete);
    }
    
    public void crearDetalleMobiliario(DetalleMobiliario detalleMobiliario) {
        Paquete paquete = new Paquete();
        Mobiliario mobiliario = new Mobiliario();
        DetalleMobiliarioPK detMobiliarioPK = new DetalleMobiliarioPK();
        detMobiliarioPK.setCodigoPaquete(paquete.getCodigo());
        detMobiliarioPK.setCodigoMobiliario(mobiliario.getCodigo());
        detalleMobiliario.setDetalleMobiliarioPK(detMobiliarioPK);
        detalleMobiliario.setValorTotal(calculateCost(detalleMobiliario.getCantidad(), mobiliario.getValor()));
        this.detallemobiliarioDAO.insert(detalleMobiliario);
    }

    public BigDecimal calculateCost(int itemQuantity, BigDecimal itemPrice) {
        BigDecimal itemCost = BigDecimal.ZERO;
        BigDecimal totalCost = BigDecimal.ZERO;
        itemCost = itemPrice.multiply(new BigDecimal(itemQuantity));
        totalCost = totalCost.add(itemCost);
        return totalCost;
    }
    
    public void actualizarDetalleMobiliario(DetalleMobiliario detalleMobiliario) {
        this.detallemobiliarioDAO.update(detalleMobiliario);
    }
}
