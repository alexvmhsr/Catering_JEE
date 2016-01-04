/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.servicio;

import com.espe.distribuidas.catering.dao.AlimentacionDAO;
import com.espe.distribuidas.catering.dao.DetalleAlimentacionDAO;
import com.espe.distribuidas.catering.dao.PaqueteDAO;
import com.espe.distribuidas.catering.modelo.Alimentacion;
import com.espe.distribuidas.catering.modelo.DetalleAlimentacion;
import com.espe.distribuidas.catering.modelo.DetalleAlimentacionPK;
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
public class AlimentacionServicio {
    
    @EJB
    private AlimentacionDAO alimentacionDAO;
    @EJB
    private DetalleAlimentacionDAO detalleAlimentacioDAO;
    
    
    @EJB
    private PaqueteDAO paqueteDAO;
    public void crearCliente(Alimentacion alimentacion) {  
            this.alimentacionDAO.insert(alimentacion);
    }
    
    public  List<Alimentacion> ObtenerTodas()
    {
        return this.alimentacionDAO.findAll();
    }
    
     public boolean validar(String nombre, String categoria, BigDecimal valor, String Detalle) {
        Alimentacion alimentacionTmp = new Alimentacion();
        alimentacionTmp.setCategoria(categoria);
        alimentacionTmp.setValor(valor);
        alimentacionTmp.setDetalle(Detalle);
        if(alimentacionDAO.find(alimentacionTmp, false).isEmpty()){
            return false;
        }else return true;
    }
     
      public void crearDetalleAlimentacion(DetalleAlimentacion detalleAlimentacion) {
        Paquete paquete = new Paquete();
        Alimentacion alimentacion = new Alimentacion();
        DetalleAlimentacionPK detalleAlimentacionPK = new DetalleAlimentacionPK ();
        detalleAlimentacionPK.setCodigoPaquete(paquete.getCodigo());
        detalleAlimentacionPK.setCodigoAlimentacion(alimentacion.getCodigo());
        detalleAlimentacion.setDetalleAlimentacionPK(detalleAlimentacionPK);
        this.detalleAlimentacioDAO.insert(detalleAlimentacion);
        
    }
    public void ActualizarAlimentacion(Alimentacion alimentacion) {
        this.alimentacionDAO.update(alimentacion);
    }
    
    public void ActualizarAlimentacionDetalle(DetalleAlimentacion detalleAlimentacion) {
        this.detalleAlimentacioDAO.update(detalleAlimentacion);
    }
    public void eliminarAlimentacion(Integer codigo) {
        Alimentacion alimentacion = this.alimentacionDAO.findById(codigo, false);
        this.alimentacionDAO.remove(alimentacion);
    }
    public List<Paquete> ObtenerPaquetes() {
        return this.paqueteDAO.findAll();
    }
    
    public List<DetalleAlimentacion> ObtenerDetalleAlimentacion() {
        return this.detalleAlimentacioDAO.findAll();
    }
}
