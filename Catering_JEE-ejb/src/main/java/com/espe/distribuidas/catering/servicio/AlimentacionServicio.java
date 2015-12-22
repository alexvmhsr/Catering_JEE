/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.servicio;

import com.espe.distribuidas.catering.dao.AlimentacionDAO;
import com.espe.distribuidas.catering.exception.ValidacionException;
import com.espe.distribuidas.catering.modelo.Alimentacion;
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
    private AlimentacionDAO alimentacionDAO= new AlimentacionDAO();
    
    
    public void crearCliente(Alimentacion alimentacion) {
        
        Alimentacion alimentacionTemp = new Alimentacion();
        alimentacionTemp.setCodigo(alimentacion.getCodigo());
        List<Alimentacion> alimentos = this.alimentacionDAO.find(alimentacionTemp);
        if (alimentos == null || alimentos.isEmpty()) {
            this.alimentacionDAO.insert(alimentacion);
        } else {
            throw new ValidacionException("El codigo de alimento: " + alimentacion.getCodigo()+ " ya existe.");
        }
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
     
    public void ActualizarAlimentacion(Alimentacion alimentacion) {
        this.alimentacionDAO.update(alimentacion);
    }
    public void eliminarAlimentacion(Integer codigo) {
        Alimentacion alimentacion = this.alimentacionDAO.findById(codigo, false);
        this.alimentacionDAO.remove(alimentacion);
    }
    
}
