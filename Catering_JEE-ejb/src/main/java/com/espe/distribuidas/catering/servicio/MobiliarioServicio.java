/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espe.distribuidas.catering.servicio;


import com.espe.distribuidas.catering.dao.MobiliarioDAO;
import com.espe.distribuidas.catering.modelo.Mobiliario;
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

    public void crearCliente(Mobiliario mobiliario) {

        this.mobiliarioDAO.insert(mobiliario);

    }

    public Mobiliario obtenerFacturaPorNumero(Integer numfactura) {
        return this.mobiliarioDAO.findById(numfactura, false);
    }

    public List<Mobiliario> obtenerClientes() {
        return this.mobiliarioDAO.findAll();
    }

    public void actualizarCliente(Mobiliario mobiliario) {
        this.mobiliarioDAO.update(mobiliario);
    }

    public void eliminarCliente(Integer codigo) {
        Mobiliario mobiliario = this.mobiliarioDAO.findById(codigo, false);
        this.mobiliarioDAO.remove(mobiliario);
    }
}
