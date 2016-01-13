/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.servicio;

import com.espe.distribuidas.catering.dao.DetalleMobiliarioDAO;
import com.espe.distribuidas.catering.dao.MobiliarioDAO;
import com.espe.distribuidas.catering.dao.TipoMobiliarioDAO;
import com.espe.distribuidas.catering.modelo.DetalleMobiliario;
import com.espe.distribuidas.catering.modelo.DetalleMobiliarioPK;
import com.espe.distribuidas.catering.modelo.Mobiliario;
import com.espe.distribuidas.catering.modelo.Paquete;
import com.espe.distribuidas.catering.modelo.TipoMobiliario;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class MobiliarioServicio {

    @EJB
    MobiliarioDAO mobiliarioDAO;

    @EJB
    TipoMobiliarioDAO tipomobiliarioDAO;

    @EJB
    DetalleMobiliarioDAO detallemobiliarioDAO;

    public void crearMobiliario(Mobiliario mobiliario) {
        this.mobiliarioDAO.insert(mobiliario);
    }

    public List<Mobiliario> obtenerTodasMobiliario() {
        return this.mobiliarioDAO.findAll();
    }

    public List<DetalleMobiliario> obtenerTodasDetalleMobiliario() {
        return this.detallemobiliarioDAO.findAll();
    }

    public Mobiliario obtenerMobiliario(Integer codmobiliario) {
        return this.mobiliarioDAO.findById(codmobiliario, false);
    }

    public List<TipoMobiliario> ObtenerTipoMobiliario() {
        return this.tipomobiliarioDAO.findAll();
    }

    public void actualizarMobiliario(Mobiliario mobiliario) {
        this.mobiliarioDAO.update(mobiliario);
    }

    public void eliminarMobiliario(Integer codigo) {
        Mobiliario mobiliario = this.mobiliarioDAO.findById(codigo, false);
        this.mobiliarioDAO.remove(mobiliario);
    }

    public void eliminarDetalleMobiliario(DetalleMobiliarioPK pk) {
        DetalleMobiliario mobiliario = this.detallemobiliarioDAO.findById(pk, true);
        this.detallemobiliarioDAO.remove(mobiliario);
    }

    public void crearDetalleMobiliario(DetalleMobiliario detalleMobiliario) {
        DetalleMobiliarioPK detMobiliarioPK = new DetalleMobiliarioPK();
        detMobiliarioPK.setCodigoPaquete(detalleMobiliario.getDetalleMobiliarioPK().getCodigoPaquete());
        detMobiliarioPK.setCodigoMobiliario(detalleMobiliario.getDetalleMobiliarioPK().getCodigoMobiliario());
        detalleMobiliario.setDetalleMobiliarioPK(detMobiliarioPK);
        
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
